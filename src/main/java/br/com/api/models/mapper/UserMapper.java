package br.com.api.models.mapper;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.models.dto.user.UserRequestDTO;
import br.com.api.models.dto.user.UserResponseDTO;
import br.com.api.models.entities.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper extends IGenericMapper<UserRequestDTO, UserResponseDTO, User> {
}
