package br.com.api.models.dto.installments;

import br.com.api.core.enums.PaymentStatus;
import br.com.api.core.generics.IGenericRequestDTO;
import br.com.api.models.dto.GenericRequestDTO;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class InstallmentRequestDTO implements IGenericRequestDTO {
    @NotNull
    private BigDecimal amount;
    private GenericRequestDTO transaction;
    private PaymentStatus paymentStatus;
    private LocalDate dueDate;
}
