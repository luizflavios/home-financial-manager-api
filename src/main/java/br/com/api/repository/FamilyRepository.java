package br.com.api.repository;

import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.models.entities.Family;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends IJpaSpecificationRepository<Family, Long> {
}
