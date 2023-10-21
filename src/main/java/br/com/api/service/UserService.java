package br.com.api.service;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.user.UserRequestDTO;
import br.com.api.models.dto.user.UserResponseDTO;
import br.com.api.models.entities.User;
import br.com.api.models.vo.EmailNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.api.core.enums.AuthenticationModelStatus.CREATED;
import static br.com.api.core.enums.NotificationType.EMAIL;
import static br.com.api.core.enums.SecurityLevel.BASIC;
import static java.util.UUID.randomUUID;

@Service
@Slf4j
public class UserService extends GenericService<UserRequestDTO, UserResponseDTO, User> {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final NotificationService notificationService;

    protected UserService(IJpaSpecificationRepository<User, Long> genericRepository,
                          @Qualifier("userMapperImpl") IGenericMapper<UserRequestDTO, UserResponseDTO, User> genericMapper, PasswordEncoder passwordEncoder, RoleService roleService, NotificationService notificationService) {
        super(genericRepository, genericMapper);
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResponseDTO create(UserRequestDTO requestDTO) {
        User entity = initialMapper(requestDTO);
        genericRepository.saveAndFlush(entity);
        this.sendWelcomeNotification(entity);
        return genericMapper.toDTO(entity);
    }

    private User initialMapper(UserRequestDTO requestDTO) {
        this.overridePropertyOnCreateForDTO(requestDTO);
        var entity = genericMapper.toEntity(requestDTO);
        this.overridePropertyOnCreateForEntity(entity);
        this.initialBaseUserPack(entity);
        return entity;
    }

    private void initialBaseUserPack(User entity) {
        entity.getAuthenticationModel().setPassword(this.passwordEncoder.encode(entity.getAuthenticationModel().getPassword()));
        entity.getAuthenticationModel().setStatus(CREATED);
        entity.getAuthenticationModel().setHash(randomUUID());
        entity.getAuthenticationModel().setRoles(this.roleService.findBySecurityLevel(BASIC));
    }

    public void sendWelcomeNotification(User user) {
        var emailNotification = new EmailNotification(user, EMAIL, "asdasdasdasd", "", "ola pequeno gafanhoto");
        notificationService.sendNotification(emailNotification);
    }

}
