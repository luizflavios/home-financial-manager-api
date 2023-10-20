package br.com.api.models.mapper;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.models.dto.role.RoleRequestDTO;
import br.com.api.models.dto.role.RoleResponseDTO;
import br.com.api.models.entities.Role;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface RoleMapper extends IGenericMapper<RoleRequestDTO, RoleResponseDTO, Role> {

}
