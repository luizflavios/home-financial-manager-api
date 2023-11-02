package br.com.api.models.dto.installments;

import br.com.api.core.generics.IGenericRequestDTO;
import br.com.api.models.dto.GenericRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class InstallmentRequestDTO implements IGenericRequestDTO {
    @NotNull
    private BigDecimal amount;
    private GenericRequestDTO transaction;
    private LocalDate dueDate;
}
