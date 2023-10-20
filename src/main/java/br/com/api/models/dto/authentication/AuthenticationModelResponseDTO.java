package br.com.api.models.dto.authentication;

import br.com.api.core.enums.AuthenticationModelStatus;
import br.com.api.core.generics.IGenericResponseDTO;
import br.com.api.models.dto.role.RoleResponseDTO;
import br.com.api.models.dto.user.UserResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class AuthenticationModelResponseDTO implements IGenericResponseDTO {
    private UUID hash;
    private String username;
    private UserResponseDTO user;
    private AuthenticationModelStatus status;
    private Set<RoleResponseDTO> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
