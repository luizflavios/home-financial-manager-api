package br.com.api.service;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.family.FamilyRequestDTO;
import br.com.api.models.dto.family.FamilyResponseDTO;
import br.com.api.models.entities.Family;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static br.com.api.core.utils.ApiUtils.getLoggedUser;

@Service
public class FamilyService extends GenericService<FamilyRequestDTO, FamilyResponseDTO, Family> {
    protected FamilyService(IJpaSpecificationRepository<Family, Long> genericRepository,
                            @Qualifier("familyMapperImpl") IGenericMapper<FamilyRequestDTO, FamilyResponseDTO, Family> genericMapper) {
        super(genericRepository, genericMapper);
    }

    private static void setDefaultUser(Family entity) {
        entity.getUsers().add(getLoggedUser());
    }

    @Override
    protected void overridePropertyOnCreateForEntity(Family entity) {
        setDefaultUser(entity);
    }

    public Family findById(Long id) {
        return this.genericRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
