package br.com.api.service;

import br.com.api.core.exception.OpenedBudgetExistsException;
import br.com.api.core.generics.FilterCriteria;
import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.budget.BudgetRequestDTO;
import br.com.api.models.dto.budget.BudgetResponseDTO;
import br.com.api.models.entities.Budget;
import br.com.api.models.entities.Income;
import br.com.api.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.lang.Boolean.TRUE;
import static java.math.BigDecimal.ZERO;
import static java.util.Objects.nonNull;

@Service
public class BudgetService extends GenericService<BudgetRequestDTO, BudgetResponseDTO, Budget> {

    private final IncomeService incomeService;
    private final FamilyService familyService;

    protected BudgetService(IJpaSpecificationRepository<Budget, Long> genericRepository,
                            @Qualifier("budgetMapperImpl") IGenericMapper<BudgetRequestDTO, BudgetResponseDTO, Budget> genericMapper, IncomeService incomeService, FamilyService familyService) {
        super(genericRepository, genericMapper);
        this.incomeService = incomeService;
        this.familyService = familyService;
    }

    private static void checkForExpenseAlert(Page<BudgetResponseDTO> budgetResponse) {
        budgetResponse.getContent().forEach(budgetResponseDTO -> {
            if (budgetResponseDTO.getBalanceDue().compareTo(budgetResponseDTO.getTotalExpense()) >= 0)
                budgetResponseDTO.setExpenseAlert(TRUE);
        });
    }

    @Override
    protected void overridePropertyOnCreateForEntity(Budget entity) {
        checkForOldBudgetsAndSetActive(entity);
        incomeCalculate(entity);
    }

    @Override
    public Page<BudgetResponseDTO> list(Pageable pageable, FilterCriteria filter) {
        var budgetResponse = super.list(pageable, filter);
        checkForExpenseAlert(budgetResponse);
        return budgetResponse;
    }

    private void incomeCalculate(Budget entity) {
        entity.setFamily(this.familyService.findById(entity.getFamily().getId()));

        entity.getFamily().getUsers().forEach(user -> {
            var incomeAmount = this.incomeService
                    .findByUser(user)
                    .stream()
                    .map(Income::getAmount)
                    .reduce(ZERO, BigDecimal::add);

            entity.setTotalIncome(nonNull(entity.getTotalIncome()) ?
                    entity.getTotalIncome().add(incomeAmount) : incomeAmount);
        });
    }

    private void checkForOldBudgetsAndSetActive(Budget entity) {
        var existsOldBudget = ((BudgetRepository) this.genericRepository).existsByFamilyId(entity.getFamily().getId());

        if (TRUE.equals(existsOldBudget)) {
            throw new OpenedBudgetExistsException();
        }

        entity.setActive(TRUE);
    }

}
