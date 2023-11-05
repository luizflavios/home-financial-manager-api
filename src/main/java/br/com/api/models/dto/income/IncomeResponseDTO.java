package br.com.api.models.dto.income;

import br.com.api.core.generics.IGenericResponseDTO;
import br.com.api.models.dto.incomecategory.IncomeCategoryResponseDTO;
import br.com.api.models.dto.user.UserResponseDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncomeResponseDTO implements IGenericResponseDTO {
    private Long id;
    private String description;
    private IncomeCategoryResponseDTO incomeCategory;
    private UserResponseDTO userResponseDTO;
}
