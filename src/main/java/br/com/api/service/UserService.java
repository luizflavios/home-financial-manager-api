package br.com.api.service;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.user.UserRequestDTO;
import br.com.api.models.dto.user.UserResponseDTO;
import br.com.api.models.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static br.com.api.core.enums.AuthenticationModelStatus.CREATED;
import static br.com.api.core.enums.SecurityLevel.BASIC;

@Service
public class UserService extends GenericService<UserRequestDTO, UserResponseDTO, User> {

    private final RoleService roleService;

    protected UserService(IJpaSpecificationRepository<User, Long> genericRepository,
                          @Qualifier("userMapperImpl") IGenericMapper<UserRequestDTO, UserResponseDTO, User> genericMapper, RoleService roleService) {
        super(genericRepository, genericMapper);
        this.roleService = roleService;
    }

    @Override
    protected void overridePropertyOnCreateForEntity(User entity) {
        super.overridePropertyOnCreateForEntity(entity);
        this.initialBaseUserPack(entity);
    }

    private void initialBaseUserPack(User entity) {
        entity.getAuthenticationModel().setStatus(CREATED);
        entity.getAuthenticationModel().setRoles(this.roleService.findBySecurityLevel(BASIC));
    }
}
