package br.com.api.models.dto.role;

import br.com.api.core.enums.SecurityLevel;
import br.com.api.core.generics.IGenericRequestDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleRequestDTO implements IGenericRequestDTO {
    @NotEmpty(message = "role name is mandatory")
    @Size(max = 255)
    private String name;
    @NotNull
    private SecurityLevel securityLevel;
}
