package com.apis.adopcionmascota.servicio;

import com.apis.adopcionmascota.dto.PersonaBasicaDto;
import com.apis.adopcionmascota.dto.PersonaDomDto;
import com.apis.adopcionmascota.dto.PersonaDto;
import com.apis.adopcionmascota.modelo.Persona;

import java.util.List;

public interface IPersonaServicio {
    List<Persona> listarPersonas();
    Persona guardarPersona(Persona persona);

    Persona buscarPersonaPorId(Long id);

    void eliminarPersona(Long id);

    PersonaBasicaDto convertirADtoBasico(Persona persona);

    PersonaDto convertirADto(Persona persona);

    Persona convertirAPersona(PersonaDomDto personaDomDto);




}
