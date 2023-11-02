package br.com.api.service;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.transaction.TransactionRequestDTO;
import br.com.api.models.dto.transaction.TransactionResponseDTO;
import br.com.api.models.entities.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static br.com.api.core.enums.PaymentStatus.OPEN;
import static br.com.api.core.enums.PaymentWay.paymentMayHaveInstallments;
import static br.com.api.core.utils.ApiUtils.getLoggedUser;

@Service
public class TransactionService extends GenericService<TransactionRequestDTO, TransactionResponseDTO, Transaction> {

    private final InstallmentService installmentService;

    protected TransactionService(IJpaSpecificationRepository<Transaction, Long> genericRepository,
                                 @Qualifier("transactionMapperImpl") IGenericMapper<TransactionRequestDTO, TransactionResponseDTO, Transaction> genericMapper, InstallmentService installmentService) {
        super(genericRepository, genericMapper);
        this.installmentService = installmentService;
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
        if (Boolean.TRUE.equals(paymentMayHaveInstallments(entity.getPaymentWay()))) {
            var installments = this.installmentService
                    .buildInstallments(entity.getAmount(), requestDTO.getInstallmentsQuantity(), requestDTO.getStartDate());

            installments.forEach(i -> i.setTransaction(entity));
            entity.setInstallments(installments);
        }
    }

    @Override
    protected void overridePropertyOnCreateForEntity(Transaction entity) {
        super.overridePropertyOnCreateForEntity(entity);
        entity.setPaymentStatus(OPEN);
        entity.setUser(getLoggedUser());
    }

}
