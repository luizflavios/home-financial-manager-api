package br.com.api.service;

import br.com.api.core.exception.AuthenticationModelNotFoundException;
import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.core.security.ApiJwtProcessor;
import br.com.api.models.dto.authentication.AuthenticationModelRequestDTO;
import br.com.api.models.dto.authentication.AuthenticationModelResponseDTO;
import br.com.api.models.dto.authentication.TokenModelDTO;
import br.com.api.models.entities.AuthenticationModel;
import br.com.api.repository.AuthenticationModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationModelService extends GenericService<AuthenticationModelRequestDTO, AuthenticationModelResponseDTO, AuthenticationModel> {

    private final ApiJwtProcessor apiJwtProcessor;

    protected AuthenticationModelService(IJpaSpecificationRepository<AuthenticationModel, Long> genericRepository,
                                         @Qualifier("authenticationModelMapperImpl") IGenericMapper<AuthenticationModelRequestDTO, AuthenticationModelResponseDTO, AuthenticationModel> genericMapper,
                                         ApiJwtProcessor apiJwtProcessor) {
        super(genericRepository, genericMapper);
        this.apiJwtProcessor = apiJwtProcessor;
    }

    public TokenModelDTO login(AuthenticationModelRequestDTO requestDTO) {
        var authenticationModel = ((AuthenticationModelRepository) this.genericRepository)
                .findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword())
                .orElseThrow(AuthenticationModelNotFoundException::new);

        return apiJwtProcessor.generateAuthentication(authenticationModel.getHash().toString());
    }
}
