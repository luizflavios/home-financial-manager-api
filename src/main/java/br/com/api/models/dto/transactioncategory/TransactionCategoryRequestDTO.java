package br.com.api.models.dto.transactioncategory;

import br.com.api.core.generics.IGenericRequestDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCategoryRequestDTO implements IGenericRequestDTO {
    @NotBlank
    private String category;
}
