package com.apis.adopcionmascota.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonaAlreadyExistsExeption extends RuntimeException{
    public PersonaAlreadyExistsExeption(String atributo) {
        super("La persona con nombre "+atributo+" ya existe");
    }
}
