package br.com.api.repository;

import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.models.entities.Income;
import br.com.api.models.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IncomeRepository extends IJpaSpecificationRepository<Income, Long> {
    @Query("select i from Income i where i.user = :user")
    Set<Income> findByUser(@Param("user") User user);
}
