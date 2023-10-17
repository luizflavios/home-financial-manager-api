package br.com.api.repository;

import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.models.entities.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends IJpaSpecificationRepository<Role, Long> {
}
