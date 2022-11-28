package com.apis.adopcionmascota.dto;

import com.apis.adopcionmascota.modelo.Animal;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class RefugioDto {

    private Long refugioId;
    private String refugioNombre;
    private String refugioCiudad;
    private String refugioDireccion;
    private List<AnimalDto> refugioAnimales;
}
