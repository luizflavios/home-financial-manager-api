package br.com.api.models.dto.transactioncategory;

import br.com.api.core.generics.IGenericRequestDTO;
import br.com.api.models.dto.GenericRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private GenericRequestDTO family;
}
