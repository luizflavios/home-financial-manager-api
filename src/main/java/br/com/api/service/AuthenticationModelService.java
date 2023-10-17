package br.com.api.service;

import br.com.api.models.dto.authentication.AuthenticationModelRequestDTO;
import br.com.api.models.dto.authentication.AuthenticationModelResponseDTO;
import br.com.api.repository.AuthenticationModelRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenticationModelService {

    private final AuthenticationModelRepository authenticationModelRepository;

    public AuthenticationModelService(AuthenticationModelRepository authenticationModelRepository) {
        this.authenticationModelRepository = authenticationModelRepository;
    }

    public AuthenticationModelResponseDTO login(AuthenticationModelRequestDTO requestDTO) {
        return AuthenticationModelResponseDTO.builder()
                .accessToken(UUID.randomUUID().toString())
                .expiresIn(System.currentTimeMillis())
                .build();
    }
}
