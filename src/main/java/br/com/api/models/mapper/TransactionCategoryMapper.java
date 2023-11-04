package br.com.api.models.mapper;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.models.dto.transactioncategory.TransactionCategoryRequestDTO;
import br.com.api.models.dto.transactioncategory.TransactionCategoryResponseDTO;
import br.com.api.models.entities.TransactionCategory;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface TransactionCategoryMapper extends IGenericMapper<TransactionCategoryRequestDTO, TransactionCategoryResponseDTO, TransactionCategory> {
}
