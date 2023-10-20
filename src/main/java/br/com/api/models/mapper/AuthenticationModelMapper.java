package br.com.api.models.mapper;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.models.dto.authentication.AuthenticationModelRequestDTO;
import br.com.api.models.dto.authentication.AuthenticationModelResponseDTO;
import br.com.api.models.entities.AuthenticationModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AuthenticationModelMapper extends IGenericMapper<AuthenticationModelRequestDTO, AuthenticationModelResponseDTO, AuthenticationModel> {
}
