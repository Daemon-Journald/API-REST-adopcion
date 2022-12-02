package com.apis.adopcionmascota.error;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Manejo de Excepcion con @ResponseStatus
 */
@Data @Getter @Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    public NotFoundException(Long id){
        super("no se encontro el elemento con el ID: "+id);
    }

    public NotFoundException() {
        super("Lista vacia");
    }
}
