package br.com.api.models.dto.installments;

import br.com.api.core.enums.PaymentStatus;
import br.com.api.core.generics.IGenericResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class InstallmentByTransactionResponseDTO implements IGenericResponseDTO {
    private Long id;
    private BigDecimal amount;
    private PaymentStatus paymentStatus;
    private LocalDate dueDate;
    private LocalDateTime paidAt;
}
