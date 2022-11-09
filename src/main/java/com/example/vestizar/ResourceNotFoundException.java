package com.example.vestizar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Resource not found exception.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Resource not found exception.
     *
     * @param mensaje the mensaje
     */
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
