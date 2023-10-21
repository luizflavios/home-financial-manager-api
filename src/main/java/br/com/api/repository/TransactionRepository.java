package br.com.api.repository;

import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.models.entities.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends IJpaSpecificationRepository<Transaction, Long> {
}
