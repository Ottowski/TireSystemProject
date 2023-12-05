package com.example.Grupp9.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ErrorDto {
    private String path;
    private String message;
    private int status;

    private LocalDateTime timestamp;
    public ErrorDto(String path, String message, int status, LocalDateTime timestamp) {
        this.path = path;
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }




    @Override
    public String toString() {
        return "ApiError{" +
                "path='" + path + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }
}
