package br.com.api.service;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.income.IncomeRequestDTO;
import br.com.api.models.dto.income.IncomeResponseDTO;
import br.com.api.models.entities.Income;
import br.com.api.models.entities.User;
import br.com.api.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;

import static br.com.api.core.utils.ApiUtils.getLoggedUser;

@Service
public class IncomeService extends GenericService<IncomeRequestDTO, IncomeResponseDTO, Income> {
    protected IncomeService(IJpaSpecificationRepository<Income, Long> genericRepository,
                            @Qualifier("incomeMapperImpl") IGenericMapper<IncomeRequestDTO, IncomeResponseDTO, Income> genericMapper) {
        super(genericRepository, genericMapper);
    }

    @Override
    protected void overridePropertyOnCreateForEntity(Income entity) {
        entity.setUser(getLoggedUser());
    }

    public Set<Income> findByUser(User user) {
        return ((IncomeRepository) this.genericRepository).findByUser(user);
    }
}
