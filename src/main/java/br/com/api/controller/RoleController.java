package br.com.api.controller;

import br.com.api.core.generics.impl.GenericController;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.role.RoleRequestDTO;
import br.com.api.models.dto.role.RoleResponseDTO;
import br.com.api.models.entities.Role;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@Tag(name = "Role", description = "Role Controller")
@Slf4j
public class RoleController extends GenericController<RoleRequestDTO, RoleResponseDTO, Role> {

    protected RoleController(GenericService<RoleRequestDTO, RoleResponseDTO, Role> service) {
        super(service);
    }

}
