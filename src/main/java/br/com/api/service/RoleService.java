package br.com.api.service;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.role.RoleRequestDTO;
import br.com.api.models.dto.role.RoleResponseDTO;
import br.com.api.models.entities.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends GenericService<RoleRequestDTO, RoleResponseDTO, Role> {

    protected RoleService(IJpaSpecificationRepository<Role, Long> genericRepository,
                          @Qualifier("roleMapper") IGenericMapper<RoleRequestDTO, RoleResponseDTO, Role> genericMapper) {
        super(genericRepository, genericMapper);
    }


}
