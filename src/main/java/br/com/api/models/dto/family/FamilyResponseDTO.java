package br.com.api.models.dto.family;

import br.com.api.core.generics.IGenericResponseDTO;
import br.com.api.models.dto.incomecategory.IncomeCategoryResponseDTO;
import br.com.api.models.dto.transactioncategory.TransactionCategoryResponseDTO;
import br.com.api.models.dto.user.UserResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class FamilyResponseDTO implements IGenericResponseDTO {
    private Long id;
    private String name;
    private Set<UserResponseDTO> users;
    private Set<TransactionCategoryResponseDTO> transactionCategories;
    private Set<IncomeCategoryResponseDTO> incomeCategories;
}
