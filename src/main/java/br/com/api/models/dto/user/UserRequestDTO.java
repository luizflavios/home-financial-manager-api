package br.com.api.models.dto.user;

import br.com.api.core.generics.IGenericRequestDTO;
import br.com.api.models.dto.authentication.AuthenticationModelRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDTO implements IGenericRequestDTO {
    @NotEmpty(message = "fullName can't be empty")
    @Size(max = 255)
    private String fullName;
    @Email
    private String email;
    @NotNull
    private AuthenticationModelRequestDTO authenticationModel;
}
