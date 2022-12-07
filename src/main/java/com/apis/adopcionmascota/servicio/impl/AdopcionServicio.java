package com.apis.adopcionmascota.servicio.impl;

import com.apis.adopcionmascota.dto.AdopcionBasicaDto;
import com.apis.adopcionmascota.dto.AdopcionDto;
import com.apis.adopcionmascota.dto.CrearAdopcionDto;
import com.apis.adopcionmascota.modelo.Adopcion;
import com.apis.adopcionmascota.modelo.Animal;
import com.apis.adopcionmascota.modelo.Persona;
import com.apis.adopcionmascota.modelo.enums.AdopcionEnum;
import com.apis.adopcionmascota.repositorio.AdopcionRespositorio;
import com.apis.adopcionmascota.servicio.IAdopcionServicio;
import com.apis.adopcionmascota.servicio.IAnimalServicio;
import com.apis.adopcionmascota.servicio.IPersonaServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdopcionServicio implements IAdopcionServicio {

    @Autowired
    private AdopcionRespositorio adopcionRespositorio;

    @Autowired
    private IAnimalServicio animalServicio;

    @Autowired
    private IPersonaServicio personaServicio;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AdopcionBasicaDto convertirDto(Adopcion adopcion){
        return modelMapper.map(adopcion, AdopcionBasicaDto.class);
    }

    @Override
    public List<Adopcion> listarAdopciones() {
        return adopcionRespositorio.findAll();
    }

    @Override
    public Adopcion guardarAdopcion(Adopcion adopcion) {
        return adopcionRespositorio.save(adopcion);
    }

    @Override
    public Optional<Adopcion> buscarAdopcionPorId(Long id) {
        return adopcionRespositorio.findById(id);
    }

    @Override
    public void eliminarAdopcion(long id) {
        Adopcion adopcion=adopcionRespositorio.findById(id).orElse(null);
        Animal animal=adopcion.getAnimal();
        animal.setEstado(AdopcionEnum.NO_ADOPTADO.toString());
        adopcionRespositorio.deleteById(id);
    }

    @Override
    public AdopcionBasicaDto convertirADtoBasico(Adopcion adopcion) {
        return modelMapper.map(adopcion, AdopcionBasicaDto.class);
    }

    @Override
    public AdopcionDto convertirADto(Adopcion adopcion) {
        return modelMapper.map(adopcion, AdopcionDto.class);
    }

    @Override
    public Adopcion convetirAObjeto(CrearAdopcionDto crearAdopcionDto) {
        Persona persona = personaServicio.buscarPersonaPorId(crearAdopcionDto.getPersonaId()).orElse(null);
        Animal animal=animalServicio.buscarAnimalPorId(crearAdopcionDto.getAnimalId()).orElse(null);

        Adopcion adopcionNuevo=new Adopcion();
        adopcionNuevo.setPersona(persona);
        animal.setEstado(AdopcionEnum.ADOPTADO.toString());
        adopcionNuevo.setAnimal(animal);

        return adopcionNuevo;
    }


}
