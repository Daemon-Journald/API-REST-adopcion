package com.apis.adopcionmascota.servicio;

import com.apis.adopcionmascota.dto.PersonaBasicaDto;
import com.apis.adopcionmascota.dto.PersonaDomDto;
import com.apis.adopcionmascota.dto.PersonaDto;
import com.apis.adopcionmascota.modelo.Persona;

import java.util.List;
import java.util.Optional;

public interface IPersonaServicio {
    List<Persona> listarPersonas();
    Persona guardarPersona(Persona persona);

    Optional<Persona> buscarPersonaPorId(Long id);

    void eliminarPersona(Long id);

    void eliminarPersonas();

    PersonaBasicaDto convertirADtoBasico(Persona persona);

    PersonaDto convertirADto(Persona persona);

    Persona convertirAPersona(PersonaDomDto personaDomDto);

    Persona validarDatosPersona(Persona persona);
}
