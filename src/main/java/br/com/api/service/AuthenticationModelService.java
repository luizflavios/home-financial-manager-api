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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationModelService extends GenericService<AuthenticationModelRequestDTO, AuthenticationModelResponseDTO, AuthenticationModel> implements UserDetailsService {

    private final ApiJwtProcessor apiJwtProcessor;
    private final PasswordEncoder passwordEncoder;

    protected AuthenticationModelService(IJpaSpecificationRepository<AuthenticationModel, Long> genericRepository,
                                         @Qualifier("authenticationModelMapperImpl") IGenericMapper<AuthenticationModelRequestDTO, AuthenticationModelResponseDTO, AuthenticationModel> genericMapper,
                                         ApiJwtProcessor apiJwtProcessor, PasswordEncoder passwordEncoder) {
        super(genericRepository, genericMapper);
        this.apiJwtProcessor = apiJwtProcessor;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenModelDTO login(AuthenticationModelRequestDTO requestDTO) {
        var authenticationModel = (AuthenticationModel) this.loadUserByUsername(requestDTO.getUsername());
        this.passwordEncoder.matches(requestDTO.getPassword(), authenticationModel.getPassword());
        return apiJwtProcessor.generateAuthentication(authenticationModel.getHash());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ((AuthenticationModelRepository) genericRepository).findByUsername(username).orElseThrow(AuthenticationModelNotFoundException::new);
    }

}
