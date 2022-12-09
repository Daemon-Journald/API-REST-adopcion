package com.apis.adopcionmascota.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    public BadRequestException(Object objeto) {
        super("error al crear "+objeto.getClass().getSimpleName());
    }
}
