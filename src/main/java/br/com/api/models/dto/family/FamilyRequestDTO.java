package br.com.api.models.dto.family;

import br.com.api.core.generics.IGenericRequestDTO;
import br.com.api.models.dto.GenericRequestDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class FamilyRequestDTO implements IGenericRequestDTO {
    @NotBlank
    private String name;
    private Set<GenericRequestDTO> users;
}
