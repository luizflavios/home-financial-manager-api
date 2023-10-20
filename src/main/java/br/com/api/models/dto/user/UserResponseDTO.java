package br.com.api.models.dto.user;

import br.com.api.core.generics.IGenericResponseDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO implements IGenericResponseDTO {
    private Long id;
    private String fullName;
    private String email;
}
