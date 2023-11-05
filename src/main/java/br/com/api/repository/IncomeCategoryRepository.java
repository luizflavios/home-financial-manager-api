package br.com.api.repository;

import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.models.entities.IncomeCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeCategoryRepository extends IJpaSpecificationRepository<IncomeCategory, Long> {
}
