package br.com.api.models.mapper;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.models.dto.income.IncomeRequestDTO;
import br.com.api.models.dto.income.IncomeResponseDTO;
import br.com.api.models.entities.Income;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface IncomeMapper extends IGenericMapper<IncomeRequestDTO, IncomeResponseDTO, Income> {
}
