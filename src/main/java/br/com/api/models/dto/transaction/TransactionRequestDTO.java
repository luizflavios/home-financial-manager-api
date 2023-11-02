package br.com.api.models.dto.transaction;

import br.com.api.core.annotations.Conditional;
import br.com.api.core.enums.PaymentWay;
import br.com.api.core.generics.IGenericRequestDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@Conditional(selected = "paymentWay", values = {"CREDIT_CARD", "FINANCING"}, required = {"installmentsQuantity", "startDate"})
public class TransactionRequestDTO implements IGenericRequestDTO {
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private PaymentWay paymentWay;
    private Integer installmentsQuantity;
    private LocalDate startDate;
    private LocalDate dueDate;
}
