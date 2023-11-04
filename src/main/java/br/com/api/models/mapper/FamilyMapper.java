package br.com.api.models.mapper;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.models.dto.family.FamilyRequestDTO;
import br.com.api.models.dto.family.FamilyResponseDTO;
import br.com.api.models.entities.Family;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface FamilyMapper extends IGenericMapper<FamilyRequestDTO, FamilyResponseDTO, Family> {
}
