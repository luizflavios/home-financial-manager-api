package br.com.api.controller;

import br.com.api.models.dto.authentication.AuthenticationModelRequestDTO;
import br.com.api.models.dto.authentication.TokenModelDTO;
import br.com.api.service.AuthenticationModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication Controller")
public class AuthenticationModelController {

    private final AuthenticationModelService authenticationModelService;

    @PostMapping("/login")
    @Operation(summary = "Login")
    public ResponseEntity<TokenModelDTO> login(@RequestBody AuthenticationModelRequestDTO authenticationModelRequestDTO) {
        return ok(authenticationModelService.login(authenticationModelRequestDTO));
    }

}
