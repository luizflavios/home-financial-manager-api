package br.com.api.models.dto.income;

import br.com.api.core.generics.IGenericRequestDTO;
import br.com.api.models.dto.GenericRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncomeRequestDTO implements IGenericRequestDTO {
    @NotBlank
    private String description;
    @NotNull
    private GenericRequestDTO incomeCategory;
}
