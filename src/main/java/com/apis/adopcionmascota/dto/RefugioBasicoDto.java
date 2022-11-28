package com.apis.adopcionmascota.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class RefugioBasicoDto {

    private Long refugioId;
    private String refugioNombre;
    private int NumeroAnimales;
}
