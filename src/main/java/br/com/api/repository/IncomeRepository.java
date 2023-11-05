package br.com.api.repository;

import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.models.entities.Income;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends IJpaSpecificationRepository<Income, Long> {
}
