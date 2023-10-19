package br.com.api.controller;

import br.com.api.models.dto.authentication.AuthenticationModelRequestDTO;
import br.com.api.models.dto.authentication.AuthenticationModelResponseDTO;
import br.com.api.service.AuthenticationModelService;
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
public class AuthenticationModelController {

    private final AuthenticationModelService authenticationModelService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationModelResponseDTO> login(@RequestBody AuthenticationModelRequestDTO authenticationModelRequestDTO) {
        return ok(authenticationModelService.login(authenticationModelRequestDTO));
    }

}
