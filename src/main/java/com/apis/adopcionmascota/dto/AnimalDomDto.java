package com.apis.adopcionmascota.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AnimalDomDto {

    private String animalNombre;
    private char animalSexo;
    private Integer animalEdad;
    private Long animalTalla;
    private String animalRaza;
    private Long refugioId;
}
