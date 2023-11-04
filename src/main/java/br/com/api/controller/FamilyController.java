package br.com.api.controller;

import br.com.api.core.generics.impl.GenericController;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.family.FamilyRequestDTO;
import br.com.api.models.dto.family.FamilyResponseDTO;
import br.com.api.models.entities.Family;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/families")
@Tag(name = "Family", description = "Family Controller")
public class FamilyController extends GenericController<FamilyRequestDTO, FamilyResponseDTO, Family> {
    protected FamilyController(GenericService<FamilyRequestDTO, FamilyResponseDTO, Family> service) {
        super(service);
    }
}
