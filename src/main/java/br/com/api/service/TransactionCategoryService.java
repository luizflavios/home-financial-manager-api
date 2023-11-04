package br.com.api.service;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.transactioncategory.TransactionCategoryRequestDTO;
import br.com.api.models.dto.transactioncategory.TransactionCategoryResponseDTO;
import br.com.api.models.entities.TransactionCategory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TransactionCategoryService extends GenericService<TransactionCategoryRequestDTO, TransactionCategoryResponseDTO, TransactionCategory> {

    protected TransactionCategoryService(IJpaSpecificationRepository<TransactionCategory, Long> genericRepository,
                                         @Qualifier("transactionCategoryMapperImpl") IGenericMapper<TransactionCategoryRequestDTO, TransactionCategoryResponseDTO,
                                                 TransactionCategory> genericMapper) {
        super(genericRepository, genericMapper);
    }
}
