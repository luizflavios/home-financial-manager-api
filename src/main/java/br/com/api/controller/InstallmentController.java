package br.com.api.controller;

import br.com.api.core.generics.FilterCriteria;
import br.com.api.core.generics.impl.GenericController;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.installments.InstallmentByTransactionResponseDTO;
import br.com.api.models.dto.installments.InstallmentRequestDTO;
import br.com.api.models.dto.installments.InstallmentResponseDTO;
import br.com.api.models.entities.Installment;
import br.com.api.service.InstallmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.api.core.constants.AppConstants.UNSUPPORTED_OPERATION;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/installments")
@Tag(name = "Installment", description = "Installment Controller")
public class InstallmentController extends GenericController<InstallmentRequestDTO, InstallmentResponseDTO, Installment> {

    protected InstallmentController(GenericService<InstallmentRequestDTO, InstallmentResponseDTO, Installment> service) {
        super(service);
    }

    @GetMapping("/transaction")
    @Operation(summary = "Get Installments by Transaction")
    public ResponseEntity<List<InstallmentByTransactionResponseDTO>> getInstallmentsByTransaction(@RequestParam Long transactionId) {
        return ok(((InstallmentService) this.service).getInstallmentsByTransaction(transactionId));
    }

    @Override
    @Operation(hidden = true)
    public ResponseEntity<InstallmentResponseDTO> save(InstallmentRequestDTO requestDTO) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    @Operation(hidden = true)
    public ResponseEntity<Object> update(Long id, InstallmentRequestDTO requestDTO) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    @Operation(hidden = true)
    public ResponseEntity<Object> delete(Long id) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    @Operation(hidden = true)
    public Page<InstallmentResponseDTO> list(Pageable pageable, FilterCriteria filter) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }
}
