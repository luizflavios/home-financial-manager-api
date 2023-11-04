package br.com.api.controller;

import br.com.api.core.generics.impl.GenericController;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.transactioncategory.TransactionCategoryRequestDTO;
import br.com.api.models.dto.transactioncategory.TransactionCategoryResponseDTO;
import br.com.api.models.entities.TransactionCategory;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions/categories")
@Tag(name = "Transaction Category", description = "Transaction Category Controller")
@Hidden
public class TransactionCategoryController extends GenericController<TransactionCategoryRequestDTO, TransactionCategoryResponseDTO, TransactionCategory> {
    protected TransactionCategoryController(GenericService<TransactionCategoryRequestDTO, TransactionCategoryResponseDTO, TransactionCategory> service) {
        super(service);
    }
}
