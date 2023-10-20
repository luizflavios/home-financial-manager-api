package br.com.api.service;

import br.com.api.core.enums.SecurityLevel;
import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.role.RoleRequestDTO;
import br.com.api.models.dto.role.RoleResponseDTO;
import br.com.api.models.entities.Role;
import br.com.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class RoleService extends GenericService<RoleRequestDTO, RoleResponseDTO, Role> {

    protected RoleService(IJpaSpecificationRepository<Role, Long> genericRepository,
                          @Qualifier("roleMapperImpl") IGenericMapper<RoleRequestDTO, RoleResponseDTO, Role> genericMapper) {
        super(genericRepository, genericMapper);
    }

    @Transactional(readOnly = true)
    public Set<Role> findBySecurityLevel(SecurityLevel securityLevel) {
        return ((RoleRepository) genericRepository).findBySecurityLevel(securityLevel);
    }

}
