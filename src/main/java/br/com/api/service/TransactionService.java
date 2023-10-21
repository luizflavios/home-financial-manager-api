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
import static br.com.api.core.utils.ApiUtils.getLoggedUser;

@Service
public class TransactionService extends GenericService<TransactionRequestDTO, TransactionResponseDTO, Transaction> {

    protected TransactionService(IJpaSpecificationRepository<Transaction, Long> genericRepository,
                                 @Qualifier("transactionMapperImpl") IGenericMapper<TransactionRequestDTO, TransactionResponseDTO, Transaction> genericMapper) {
        super(genericRepository, genericMapper);
    }

    @Override
    protected void overridePropertyOnCreateForEntity(Transaction entity) {
        super.overridePropertyOnCreateForEntity(entity);
        entity.setPaymentStatus(OPEN);
        entity.setUser(getLoggedUser());
    }

}
