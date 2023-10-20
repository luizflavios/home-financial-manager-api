package br.com.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.api.core.constants.AppConstants.HEALTH_CHECK_UP;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/health")
@Tag(name = "Health", description = "Health Controller")
public class HealthCheckController {

    @GetMapping
    @Operation(summary = "Health Check")
    public ResponseEntity<String> healthCheck() {
        return ok(HEALTH_CHECK_UP);
    }

}
