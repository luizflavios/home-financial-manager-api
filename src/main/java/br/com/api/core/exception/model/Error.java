package br.com.api.core.exception.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Error {
    private String cause;
    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
}
