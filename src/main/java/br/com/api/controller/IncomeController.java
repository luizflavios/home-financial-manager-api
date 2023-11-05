package br.com.api.controller;

import br.com.api.core.generics.impl.GenericController;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.income.IncomeRequestDTO;
import br.com.api.models.dto.income.IncomeResponseDTO;
import br.com.api.models.entities.Income;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/incomes")
@Tag(name = "Income", description = "Income Controller")
public class IncomeController extends GenericController<IncomeRequestDTO, IncomeResponseDTO, Income> {
    protected IncomeController(GenericService<IncomeRequestDTO, IncomeResponseDTO, Income> service) {
        super(service);
    }
}
