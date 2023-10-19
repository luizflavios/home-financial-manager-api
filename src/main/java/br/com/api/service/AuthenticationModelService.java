package br.com.api.service;

import br.com.api.core.exception.AuthenticationModelNotFoundException;
import br.com.api.core.security.ApiJwtProcessor;
import br.com.api.models.dto.authentication.AuthenticationModelRequestDTO;
import br.com.api.models.dto.authentication.AuthenticationModelResponseDTO;
import br.com.api.repository.AuthenticationModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationModelService {

    private final AuthenticationModelRepository authenticationModelRepository;
    private final ApiJwtProcessor apiJwtProcessor;

    public AuthenticationModelResponseDTO login(AuthenticationModelRequestDTO requestDTO) {
        var authenticationModel = this.authenticationModelRepository
                .findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword())
                .orElseThrow(AuthenticationModelNotFoundException::new);

        return apiJwtProcessor.generateAuthentication(authenticationModel.getHash().toString());
    }
}
