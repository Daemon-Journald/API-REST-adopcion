package com.apis.adopcionmascota.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter
public class AdopcionDto {

    private Long adopcionId;
    private Date adopcionFecha;
    private AnimalDto adopcionAnimal;
    private PersonaDomDto adopcionPersona;
}
