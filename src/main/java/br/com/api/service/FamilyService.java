package br.com.api.service;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.family.FamilyRequestDTO;
import br.com.api.models.dto.family.FamilyResponseDTO;
import br.com.api.models.entities.Family;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FamilyService extends GenericService<FamilyRequestDTO, FamilyResponseDTO, Family> {
    protected FamilyService(IJpaSpecificationRepository<Family, Long> genericRepository,
                            @Qualifier("familyMapperImpl") IGenericMapper<FamilyRequestDTO, FamilyResponseDTO, Family> genericMapper) {
        super(genericRepository, genericMapper);
    }
}
