package br.com.api.models.dto.incomecategory;

import br.com.api.core.generics.IGenericResponseDTO;
import br.com.api.models.dto.family.FamilyResponseDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncomeCategoryResponseDTO implements IGenericResponseDTO {
    private Long id;
    private String category;
    private FamilyResponseDTO family;
}
