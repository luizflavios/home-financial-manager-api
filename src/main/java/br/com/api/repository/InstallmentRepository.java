package br.com.api.repository;

import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.models.entities.Installment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstallmentRepository extends IJpaSpecificationRepository<Installment, Long> {
    @Query("select i from Installment i where i.transaction.id = ?1")
    List<Installment> findByTransactionId(Long transactionId);
}
