package br.com.api.models.dto.transaction;

import br.com.api.core.annotations.Conditional;
import br.com.api.core.enums.PaymentWay;
import br.com.api.core.generics.IGenericRequestDTO;
import br.com.api.models.dto.GenericRequestDTO;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@Conditional(
        selected = "paymentWay",
        values = {"CREDIT_CARD", "FINANCING"},
        required = {"installmentsQuantity", "startDate"},
        message = "types of installment payments require installments and payment start date."
)
public class TransactionRequestDTO implements IGenericRequestDTO {
    @NotBlank
    private String description;
    @NotNull("transaction category can't be null")
    private GenericRequestDTO transactionCategory;
    @NotNull("budget can't be null")
    private GenericRequestDTO budget;
    @NotNull
    @DecimalMin(value = "0.1", message = "amount can't be zero", inclusive = false)
    private BigDecimal amount;
    @NotNull
    private PaymentWay paymentWay;
    private Integer installmentsQuantity;
    private LocalDate startDate;
    private LocalDate dueDate;
}
