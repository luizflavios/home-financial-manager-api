package br.com.api.models.dto.role;

import br.com.api.core.enums.SecurityLevel;
import br.com.api.core.generics.IGenericResponseDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleResponseDTO implements IGenericResponseDTO {
    private Long id;
    private String name;
    private SecurityLevel securityLevel;
}
