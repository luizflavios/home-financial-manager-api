package br.com.api.controller;

import br.com.api.models.entities.AuthenticationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthenticationModelController {

    @PostMapping("/login")
    public ResponseEntity<AuthenticationModel> login(@RequestBody AuthenticationModel authenticationModel) {
        return ok(authenticationModel);
    }

}
