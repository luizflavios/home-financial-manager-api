package br.com.api.models.mapper;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.models.dto.incomecategory.IncomeCategoryRequestDTO;
import br.com.api.models.dto.incomecategory.IncomeCategoryResponseDTO;
import br.com.api.models.entities.IncomeCategory;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface IncomeCategoryMapper extends IGenericMapper<IncomeCategoryRequestDTO, IncomeCategoryResponseDTO, IncomeCategory> {
}
