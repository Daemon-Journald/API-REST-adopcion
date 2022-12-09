package com.apis.adopcionmascota.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class PersonaDto {

    private Long personaId;
    private String personaNombre;
    private String personaEmail;
    private String personaDireccion;
    private String personaTelefono;

    private List<PersonaAdopcionDto> personaAdopciones;
}
