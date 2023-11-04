package br.com.api.repository;

import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.models.entities.TransactionCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionCategoryRepository extends IJpaSpecificationRepository<TransactionCategory, Long> {
}
