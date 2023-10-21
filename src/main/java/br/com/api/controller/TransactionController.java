package br.com.api.controller;

import br.com.api.core.generics.impl.GenericController;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.transaction.TransactionRequestDTO;
import br.com.api.models.dto.transaction.TransactionResponseDTO;
import br.com.api.models.entities.Transaction;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transaction", description = "Transaction Controller")
public class TransactionController extends GenericController<TransactionRequestDTO, TransactionResponseDTO, Transaction> {

    protected TransactionController(GenericService<TransactionRequestDTO, TransactionResponseDTO, Transaction> service) {
        super(service);
    }
    
}
