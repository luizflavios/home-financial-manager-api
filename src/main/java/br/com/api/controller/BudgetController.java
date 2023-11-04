package br.com.api.controller;

import br.com.api.core.generics.impl.GenericController;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.budget.BudgetRequestDTO;
import br.com.api.models.dto.budget.BudgetResponseDTO;
import br.com.api.models.entities.Budget;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budgets")
@Tag(name = "Budget", description = "Budget Controller")
public class BudgetController extends GenericController<BudgetRequestDTO, BudgetResponseDTO, Budget> {

    protected BudgetController(GenericService<BudgetRequestDTO, BudgetResponseDTO, Budget> service) {
        super(service);
    }
}
