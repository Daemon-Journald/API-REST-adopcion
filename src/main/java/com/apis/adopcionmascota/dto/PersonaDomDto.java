package com.apis.adopcionmascota.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class PersonaDomDto {

    private Long personaId;
    private String personaNombre;
    private String personaEmail;
    private String personaDireccion;
    private String personaTelefono;
}
