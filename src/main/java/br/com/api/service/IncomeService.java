package br.com.api.service;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.income.IncomeRequestDTO;
import br.com.api.models.dto.income.IncomeResponseDTO;
import br.com.api.models.entities.Income;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class IncomeService extends GenericService<IncomeRequestDTO, IncomeResponseDTO, Income> {
    protected IncomeService(IJpaSpecificationRepository<Income, Long> genericRepository,
                            @Qualifier("incomeMapperImpl") IGenericMapper<IncomeRequestDTO, IncomeResponseDTO, Income> genericMapper) {
        super(genericRepository, genericMapper);
    }
}
