package br.com.api.models.dto.transaction;

import br.com.api.core.enums.PaymentWay;
import br.com.api.core.generics.IGenericRequestDTO;
import br.com.api.models.dto.installments.InstallmentRequestDTO;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class TransactionRequestDTO implements IGenericRequestDTO {
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private PaymentWay paymentWay;
    private LocalDate dueDate;
    @NotNull
    private Boolean recursivePayment;
    private Integer quantityOfInstallments;
    private LocalDate startOfBilling;

    private Set<InstallmentRequestDTO> installments;
}
