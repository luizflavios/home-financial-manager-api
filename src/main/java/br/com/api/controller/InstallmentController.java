package br.com.api.controller;

import br.com.api.core.generics.impl.GenericController;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.installments.InstallmentRequestDTO;
import br.com.api.models.dto.installments.InstallmentResponseDTO;
import br.com.api.models.entities.Installment;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/installments")
@Tag(name = "Installment", description = "Installment Controller")
public class InstallmentController extends GenericController<InstallmentRequestDTO, InstallmentResponseDTO, Installment> {
    protected InstallmentController(GenericService<InstallmentRequestDTO, InstallmentResponseDTO, Installment> service) {
        super(service);
    }
}
