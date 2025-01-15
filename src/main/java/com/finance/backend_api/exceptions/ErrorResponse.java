package com.finance.backend_api.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private String status;
    private String message;
    private LocalDateTime timestamp;
    private String path;

    public ErrorResponse(String status, String message, String path) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }

    // Getters y setters
}
