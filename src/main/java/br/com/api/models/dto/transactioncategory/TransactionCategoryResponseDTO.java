package br.com.api.models.dto.transactioncategory;

import br.com.api.core.generics.IGenericResponseDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionCategoryResponseDTO implements IGenericResponseDTO {
    private Long id;
    private String category;
}
