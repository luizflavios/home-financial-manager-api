package br.com.api.service;

import br.com.api.core.enums.PaymentStatus;
import br.com.api.core.enums.PaymentWay;
import br.com.api.core.exception.OpenedInstallmentNotFoundException;
import br.com.api.core.exception.PaymentMethodNotAllowedException;
import br.com.api.core.generics.FilterCriteria;
import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.AbstractFilterSpecification;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.transaction.TransactionRequestDTO;
import br.com.api.models.dto.transaction.TransactionResponseDTO;
import br.com.api.models.entities.Transaction;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static br.com.api.core.enums.PaymentStatus.OPEN;
import static br.com.api.core.enums.PaymentStatus.PAID;
import static br.com.api.core.enums.PaymentWay.paymentMayHaveInstallments;
import static br.com.api.core.utils.ApiUtils.getLoggedUser;
import static br.com.api.core.utils.MathUtils.ZERO;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class TransactionService extends GenericService<TransactionRequestDTO, TransactionResponseDTO, Transaction> {

    private final InstallmentService installmentService;

    protected TransactionService(IJpaSpecificationRepository<Transaction, Long> genericRepository,
                                 @Qualifier("transactionMapperImpl") IGenericMapper<TransactionRequestDTO, TransactionResponseDTO, Transaction> genericMapper, InstallmentService installmentService) {
        super(genericRepository, genericMapper);
        this.installmentService = installmentService;
    }

    @Override
    protected Specification<Transaction> buildDefaultSpecification(FilterCriteria filter) {
        return new AbstractFilterSpecification<>(filter) {
            @Override
            public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return super.buildFilterPredicate(root, criteriaBuilder);
            }

            @Override
            protected Object convertToPredicateValue(String property, String value) {
                if ("paymentStatus".equals(property)) {
                    return PaymentStatus.valueOf(value);
                } else if ("paymentWay".equals(property)) {
                    return PaymentWay.valueOf(value);
                }
                return super.convertToPredicateValue(property, value);
            }
        };
    }

    @Override
    public TransactionResponseDTO create(TransactionRequestDTO requestDTO) {
        this.overridePropertyOnCreateForDTO(requestDTO);
        var entity = genericMapper.toEntity(requestDTO);
        this.overridePropertyOnCreateForEntity(entity);
        checkForInstallments(requestDTO, entity);
        genericRepository.saveAndFlush(entity);
        return genericMapper.toDTO(entity);
    }

    private void checkForInstallments(TransactionRequestDTO requestDTO, Transaction entity) {
        if (Boolean.TRUE.equals(paymentMayHaveInstallments(entity.getPaymentWay())) && requestDTO.getInstallmentsQuantity() > ZERO) {
            var installments = this.installmentService
                    .buildInstallments(entity.getAmount(), requestDTO.getInstallmentsQuantity(), requestDTO.getStartDate());

            installments.forEach(i -> i.setTransaction(entity));
            entity.setInstallments(installments);
        } else {
            entity.setPaymentStatus(PAID);
        }
    }

    @Override
    protected void overridePropertyOnCreateForEntity(Transaction entity) {
        super.overridePropertyOnCreateForEntity(entity);
        entity.setPaymentStatus(OPEN);
        entity.setUser(getLoggedUser());
    }

    public void monthlyPaid(@NotNull Long transactionId) {
        var transaction = this.genericRepository.findById(transactionId)
                .orElseThrow(EntityNotFoundException::new);

        if (FALSE.equals(paymentMayHaveInstallments(transaction.getPaymentWay()))) {
            throw new PaymentMethodNotAllowedException("ref transaction dont have payment by installments");
        }

        var installments = transaction.getInstallments()
                .stream()
                .filter(i -> OPEN.equals(i.getPaymentStatus()))
                .toList();

        if (installments.isEmpty()) {
            throw new OpenedInstallmentNotFoundException();
        } else if (installments.size() == 1) {
            transaction.setPaymentStatus(PAID);
            this.genericRepository.save(transaction);
        }

        this.installmentService.payInstallment(installments.get(0));
    }

    public void payOff(Long transactionId) {
        var transaction = this.genericRepository.findById(transactionId)
                .orElseThrow(EntityNotFoundException::new);

        if (TRUE.equals(paymentMayHaveInstallments(transaction.getPaymentWay()))) {
            transaction.getInstallments().forEach(installmentService::payInstallment);
        }

        transaction.setPaymentStatus(PAID);
        this.genericRepository.saveAndFlush(transaction);
    }
}
