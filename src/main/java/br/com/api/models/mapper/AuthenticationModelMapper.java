package br.com.api.models.mapper;

import br.com.api.models.dto.authentication.AuthenticationModelRequestDTO;
import br.com.api.models.dto.authentication.AuthenticationModelResponseDTO;
import br.com.api.models.entities.AuthenticationModel;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface AuthenticationModelMapper {
    AuthenticationModelMapper INSTANCE = getMapper(AuthenticationModelMapper.class);

    AuthenticationModel toEntity(AuthenticationModelRequestDTO requestDTO);

    AuthenticationModelResponseDTO toDTO(AuthenticationModel model);
}
