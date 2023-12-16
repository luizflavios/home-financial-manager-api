package br.com.api.models.dto.income;

import br.com.api.core.generics.IGenericRequestDTO;
import br.com.api.models.dto.GenericRequestDTO;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class IncomeRequestDTO implements IGenericRequestDTO {
    @NotBlank
    private String description;
    @NotNull
    @DecimalMin(value = "0.1")
    private BigDecimal amount;
    @NotNull
    private GenericRequestDTO incomeCategory;
}
