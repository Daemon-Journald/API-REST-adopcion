package com.apis.adopcionmascota.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AnimalDto {

    private Long animalId;
    private String animalNombre;
    private String animalRaza;
    private char animalSexo;
    private Integer animalEdad;
    private Long animalTalla;
    private RefugioBasicoDto animalRefugio;
}
