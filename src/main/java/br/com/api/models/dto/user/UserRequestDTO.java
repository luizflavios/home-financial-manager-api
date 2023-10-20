package br.com.api.models.dto.user;

import br.com.api.core.generics.IGenericRequestDTO;
import br.com.api.models.dto.authentication.AuthenticationModelRequestDTO;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
