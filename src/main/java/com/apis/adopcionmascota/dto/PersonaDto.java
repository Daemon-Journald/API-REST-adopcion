package com.apis.adopcionmascota.dto;

import com.apis.adopcionmascota.modelo.Adopcion;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter @Setter
public class PersonaDto {
    private Long personaId;
    private String personaNombre;
    private String personaEmail;
    private String personaDireccion;
    private String personaTelefono;
    private List<AdopcionPersonaDto> personaAdopciones;
}
