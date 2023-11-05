package br.com.api.service;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.incomecategory.IncomeCategoryRequestDTO;
import br.com.api.models.dto.incomecategory.IncomeCategoryResponseDTO;
import br.com.api.models.entities.IncomeCategory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class IncomeCategoryService extends GenericService<IncomeCategoryRequestDTO, IncomeCategoryResponseDTO, IncomeCategory> {
    protected IncomeCategoryService(IJpaSpecificationRepository<IncomeCategory, Long> genericRepository,
                                    @Qualifier("incomeCategoryMapperImpl") IGenericMapper<IncomeCategoryRequestDTO, IncomeCategoryResponseDTO, IncomeCategory> genericMapper) {
        super(genericRepository, genericMapper);
    }
}
