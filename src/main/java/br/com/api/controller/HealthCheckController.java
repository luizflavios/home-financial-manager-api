package br.com.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.api.core.constants.AppConstants.HEALTH_CHECK_UP;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return ok(HEALTH_CHECK_UP);
    }

}
