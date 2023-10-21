package br.com.api.models.dto.installments;

import br.com.api.core.enums.PaymentStatus;
import br.com.api.core.generics.IGenericResponseDTO;
import br.com.api.models.dto.transaction.TransactionResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class InstallmentResponseDTO implements IGenericResponseDTO {
    private Long id;
    private BigDecimal amount;
    private TransactionResponseDTO transaction;
    private PaymentStatus paymentStatus;
    private LocalDate dueDate;
}
