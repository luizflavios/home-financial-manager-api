package br.com.api.service;

import br.com.api.core.exception.PaymentMethodNotAllowedException;
import br.com.api.core.exception.RecursivePaymentNeedInstallmentException;
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
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

@Service
public class TransactionService extends GenericService<TransactionRequestDTO, TransactionResponseDTO, Transaction> {

    private final InstallmentService installmentService;

    protected TransactionService(IJpaSpecificationRepository<Transaction, Long> genericRepository,
                                 @Qualifier("transactionMapperImpl") IGenericMapper<TransactionRequestDTO, TransactionResponseDTO, Transaction> genericMapper, InstallmentService installmentService) {
        super(genericRepository, genericMapper);
        this.installmentService = installmentService;
    }

    @Override
    protected void overridePropertyOnCreateForDTO(TransactionRequestDTO requestDTO) {
        super.overridePropertyOnCreateForDTO(requestDTO);
        checkForRecursiveInstallments(requestDTO);
    }

    @Override
    protected void overridePropertyOnCreateForEntity(Transaction entity) {
        super.overridePropertyOnCreateForEntity(entity);
        entity.setPaymentStatus(OPEN);
        checkForInstallments(entity);
    }

    private void checkForInstallments(Transaction entity) {
        if (TRUE.equals(paymentMayHaveInstallments(entity.getPaymentWay())) && !entity.getInstallments().isEmpty()) {

            entity.getInstallments().forEach(installment -> {
                installment.setPaymentStatus(OPEN);
                installment.setTransaction(entity);
            });

        } else if (FALSE.equals(paymentMayHaveInstallments(entity.getPaymentWay())) && entity.getInstallments().isEmpty()) {
            throw new PaymentMethodNotAllowedException("chosen payment way is not allowed when exists installments");
        }
    }


    private void checkForRecursiveInstallments(TransactionRequestDTO requestDTO) {
        if (TRUE.equals(requestDTO.getRecursivePayment())) {
            if (isNull(requestDTO.getQuantityOfInstallments())) {
                throw new RecursivePaymentNeedInstallmentException("recursive payment needs installments");
            }

            requestDTO.setInstallments(this.installmentService.buildRecursiveInstallments(requestDTO));
        }
    }
}
