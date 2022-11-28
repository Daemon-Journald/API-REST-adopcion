package com.apis.adopcionmascota.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter
public class AdopcionBasicaDto {

    private Long adopcionId;
    private Date adopcionFecha;
    private String animalNombre;
    private String personaNombre;
}
