package br.com.api.repository;

import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.models.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IJpaSpecificationRepository<User, Long> {
}
