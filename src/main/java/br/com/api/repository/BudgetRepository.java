package br.com.api.repository;

import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.models.entities.Budget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends IJpaSpecificationRepository<Budget, Long> {
    @Query("select case when count(b) > 0  then true else false end from Budget b where b.family.id = ?1 and b.active = true")
    Boolean existsByFamilyId(Long id);
}
