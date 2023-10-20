package br.com.api.controller;

import br.com.api.core.generics.impl.GenericController;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.user.UserRequestDTO;
import br.com.api.models.dto.user.UserResponseDTO;
import br.com.api.models.entities.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "User Controller")
public class UserController extends GenericController<UserRequestDTO, UserResponseDTO, User> {
    protected UserController(GenericService<UserRequestDTO, UserResponseDTO, User> service) {
        super(service);
    }
}
