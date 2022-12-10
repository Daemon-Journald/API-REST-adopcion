package com.apis.adopcionmascota.servicio.impl;

import com.apis.adopcionmascota.dto.PersonaBasicaDto;
import com.apis.adopcionmascota.dto.PersonaDto;
import com.apis.adopcionmascota.error.BadRequestException;
import com.apis.adopcionmascota.error.PersonaAlreadyExistsExeption;
import com.apis.adopcionmascota.modelo.Persona;
import com.apis.adopcionmascota.repositorio.PersonaRepositorio;
import com.apis.adopcionmascota.servicio.IPersonaServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServicio implements IPersonaServicio {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    public List<Persona> listarPersonas(){ return personaRepositorio.findAll(); }

    public Persona guardarPersona(Persona persona){
        return personaRepositorio.save(persona);

    }

    public Optional<Persona> buscarPersonaPorId(Long id){
        return personaRepositorio.findById(id);
    }

    public void eliminarPersona(Long id){
        personaRepositorio.deleteById(id);
    }

    @Override
    public void eliminarPersonas() {
        personaRepositorio.deleteAll();
    }

    /**
     * Convierte persona a PersonaBasicaDto
     * @param persona
     * @return PersonaBasicaDto
     */
    public PersonaBasicaDto convertirADtoBasico(Persona persona){
        return modelMapper.map(persona, PersonaBasicaDto.class);
    }

    @Override
    public PersonaDto convertirADto(Persona persona) {
        return modelMapper.map(persona, PersonaDto.class);
    }

    @Override
    public Persona validarDatosPersona(Persona persona){
        if(persona.getNombre().equals("") ||persona.getDireccion().equals("")||persona.getTelefono().equals("")){
                throw new BadRequestException(persona);
        }
        return persona;
    }


}
