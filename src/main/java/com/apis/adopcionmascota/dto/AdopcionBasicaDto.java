package com.apis.adopcionmascota.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter
public class AdopcionBasicaDto {

    private Long adopcionId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date adopcionFecha;
    private String animalNombre;
    private String personaNombre;
}
