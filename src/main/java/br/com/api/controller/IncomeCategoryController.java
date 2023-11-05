package br.com.api.controller;

import br.com.api.core.generics.impl.GenericController;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.incomecategory.IncomeCategoryRequestDTO;
import br.com.api.models.dto.incomecategory.IncomeCategoryResponseDTO;
import br.com.api.models.entities.IncomeCategory;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/incomes/categories")
@Tag(name = "Income Category", description = "Income Category Controller")
public class IncomeCategoryController extends GenericController<IncomeCategoryRequestDTO, IncomeCategoryResponseDTO, IncomeCategory> {
    protected IncomeCategoryController(GenericService<IncomeCategoryRequestDTO, IncomeCategoryResponseDTO, IncomeCategory> service) {
        super(service);
    }
}
