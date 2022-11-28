package com.apis.adopcionmascota.servicio;

import com.apis.adopcionmascota.dto.AdopcionBasicaDto;
import com.apis.adopcionmascota.dto.AdopcionDomDto;
import com.apis.adopcionmascota.dto.AdopcionDto;
import com.apis.adopcionmascota.modelo.Adopcion;
import com.apis.adopcionmascota.modelo.Animal;
import com.apis.adopcionmascota.modelo.Persona;
import com.apis.adopcionmascota.repositorio.AdopcionRespositorio;
import com.apis.adopcionmascota.repositorio.AnimalRepositorio;
import com.apis.adopcionmascota.repositorio.PersonaRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdopcionServicio implements IAdopcionServicio{

    @Autowired
    private AdopcionRespositorio adopcionRespositorio;

    @Autowired
    private AnimalRepositorio animalRepositorio;

    @Autowired
    private PersonaRepositorio personaRepositorio;

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
    public Adopcion buscarAdopcionPorId(Long id) {
        return adopcionRespositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminarAdopcion(long id) {
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
    public Adopcion convetirAObjeto(AdopcionDomDto adopcionDomDto) {
        Persona personaNueva=new Persona();
        personaNueva.setNombre(adopcionDomDto.getPersonaNombre());
        personaNueva.setTelefono(adopcionDomDto.getPersonaTelefono());
        personaNueva.setDireccion(adopcionDomDto.getPersonaDireccion());
        personaNueva.setEmail(adopcionDomDto.getPersonaEmail());
        personaNueva.setAdopciones(new ArrayList<>());
        personaRepositorio.save(personaNueva);

        Adopcion adopcionNuevo=new Adopcion();
        adopcionNuevo.setPersona(personaNueva);
        adopcionNuevo.setFecha(new Date());

        Animal animal=animalRepositorio.findById(adopcionDomDto.getAnimalId()).orElse(null);
        adopcionNuevo.setAnimal(animal);

        List<Adopcion> listaAdopciones=personaNueva.getAdopciones();
        listaAdopciones.add(adopcionNuevo);
        //aumentado
        personaNueva.setAdopciones(listaAdopciones);
        return adopcionNuevo;
    }


}
