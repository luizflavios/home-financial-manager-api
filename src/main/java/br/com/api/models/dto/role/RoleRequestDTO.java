package br.com.api.models.dto.role;

import br.com.api.core.enums.SecurityLevel;
import br.com.api.core.generics.IGenericRequestDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RoleRequestDTO implements IGenericRequestDTO {
    @NotEmpty(message = "role name is mandatory")
    @Size(max = 255)
    private String name;
    @NotNull
    private SecurityLevel securityLevel;
}
