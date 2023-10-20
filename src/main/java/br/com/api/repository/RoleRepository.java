package br.com.api.repository;

import br.com.api.core.enums.SecurityLevel;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.models.entities.Role;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends IJpaSpecificationRepository<Role, Long> {
    Set<Role> findBySecurityLevel(SecurityLevel securityLevel);
}
