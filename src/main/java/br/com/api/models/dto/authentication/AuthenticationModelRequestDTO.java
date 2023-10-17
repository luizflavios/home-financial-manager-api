package br.com.api.models.dto.authentication;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class AuthenticationModelRequestDTO {
    @NotEmpty(message = "username can't be empty or null")
    private String username;
    @NotEmpty(message = "password can't be empty or null")
    private String password;
}
