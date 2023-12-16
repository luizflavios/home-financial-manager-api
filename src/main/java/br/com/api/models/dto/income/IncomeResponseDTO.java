package br.com.api.models.dto.income;

import br.com.api.core.generics.IGenericResponseDTO;
import br.com.api.models.dto.incomecategory.IncomeCategoryResponseDTO;
import br.com.api.models.dto.user.UserResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class IncomeResponseDTO implements IGenericResponseDTO {
    private Long id;
    private String description;
    private BigDecimal amount;
    private IncomeCategoryResponseDTO incomeCategory;
    private UserResponseDTO userResponseDTO;
}
