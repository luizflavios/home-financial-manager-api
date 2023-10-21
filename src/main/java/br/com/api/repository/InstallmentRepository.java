package br.com.api.repository;

import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.models.entities.Installment;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository extends IJpaSpecificationRepository<Installment, Long> {
}
