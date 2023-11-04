package br.com.api.service;

import br.com.api.core.exception.OpenedBudgetExistsException;
import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.budget.BudgetRequestDTO;
import br.com.api.models.dto.budget.BudgetResponseDTO;
import br.com.api.models.entities.Budget;
import br.com.api.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.TRUE;

@Service
public class BudgetService extends GenericService<BudgetRequestDTO, BudgetResponseDTO, Budget> {
    
    protected BudgetService(IJpaSpecificationRepository<Budget, Long> genericRepository,
                            @Qualifier("budgetMapperImpl") IGenericMapper<BudgetRequestDTO, BudgetResponseDTO, Budget> genericMapper) {
        super(genericRepository, genericMapper);
    }

    @Override
    protected void overridePropertyOnCreateForEntity(Budget entity) {
        checkForOldBudgetsAndSetActive(entity);
    }

    private void checkForOldBudgetsAndSetActive(Budget entity) {
        var existsOldBudget = ((BudgetRepository) this.genericRepository).existsByFamilyId(entity.getFamily().getId());

        if (TRUE.equals(existsOldBudget)) {
            throw new OpenedBudgetExistsException();
        }

        entity.setActive(TRUE);
    }
}
