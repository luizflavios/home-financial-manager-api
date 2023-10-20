package br.com.api.models.dto.authentication;

import br.com.api.core.generics.IGenericRequestDTO;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class AuthenticationModelRequestDTO implements IGenericRequestDTO {
    @NotEmpty(message = "username can't be empty or null")
    private String username;
    @NotEmpty(message = "password can't be empty or null")
    private String password;
}
