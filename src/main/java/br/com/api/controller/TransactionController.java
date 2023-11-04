package br.com.api.controller;

import br.com.api.core.generics.impl.GenericController;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.transaction.TransactionRequestDTO;
import br.com.api.models.dto.transaction.TransactionResponseDTO;
import br.com.api.models.entities.Transaction;
import br.com.api.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transaction", description = "Transaction Controller")
public class TransactionController extends GenericController<TransactionRequestDTO, TransactionResponseDTO, Transaction> {

    protected TransactionController(GenericService<TransactionRequestDTO, TransactionResponseDTO, Transaction> service) {
        super(service);
    }

    @PatchMapping("/monthly/paid")
    @Operation(summary = "Monthly paid by Transaction")
    public ResponseEntity<Void> monthlyPaid(@RequestParam Long transaction) {
        ((TransactionService) this.service).monthlyPaid(transaction);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/payoff")
    @Operation(summary = "Pay off Transaction")
    public ResponseEntity<Void> payOff(@RequestParam Long transaction) {
        ((TransactionService) this.service).payOff(transaction);
        return ResponseEntity.noContent().build();
    }

}
