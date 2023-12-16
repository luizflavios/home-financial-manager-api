package br.com.api.models.dto.budget;

import br.com.api.core.generics.IGenericRequestDTO;
import br.com.api.models.dto.GenericRequestDTO;
import jakarta.validation.constraints.DecimalMin;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Data
@Builder
public class BudgetRequestDTO implements IGenericRequestDTO {
    @NotNull
    private GenericRequestDTO family;
    @NotNull
    @DecimalMin(value = "0.1", message = "totalExpense can't be zero", inclusive = false)
    private BigDecimal totalExpense;
}
