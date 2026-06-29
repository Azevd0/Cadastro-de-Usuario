package br.com.davyson.userregistryapi.domain.exceptions.globalexceptions;

import java.time.LocalDateTime;

public class StandardError {
    private LocalDateTime timestamp;
    private Integer statusCode;
    private String message;
    private String uri;

    public StandardError(LocalDateTime timestamp, Integer statusCode, String message, String uri) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.message = message;
        this.uri = uri;
    }
}
