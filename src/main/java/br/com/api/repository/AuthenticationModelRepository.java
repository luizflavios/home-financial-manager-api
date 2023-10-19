package br.com.api.repository;

import br.com.api.models.entities.AuthenticationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthenticationModelRepository extends CrudRepository<AuthenticationModel, Long> {
    Optional<AuthenticationModel> findByHash(UUID hash);

    Optional<AuthenticationModel> findByUsernameAndPassword(String username, String password);
}
