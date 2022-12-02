package com.apis.adopcionmascota.servicio.impl;

import com.apis.adopcionmascota.dto.AnimalBasicoDto;
import com.apis.adopcionmascota.dto.AnimalDomDto;
import com.apis.adopcionmascota.dto.AnimalDto;
import com.apis.adopcionmascota.modelo.Animal;
import com.apis.adopcionmascota.modelo.Refugio;
import com.apis.adopcionmascota.repositorio.AnimalRepositorio;
import com.apis.adopcionmascota.repositorio.RefugioRepositorio;
import com.apis.adopcionmascota.servicio.IAnimalServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServicio implements IAnimalServicio {
    @Autowired
    private AnimalRepositorio animalRepositorio;

    @Autowired
    private RefugioRepositorio refugioRepositorio;

    @Autowired
    private RefugioServicio refugioServicio;


    @Autowired
    private ModelMapper modelMapper;

    public List<Animal> listarAnimales(){ return animalRepositorio.findAll(); }

    public Animal guardarAnimales(Animal animal){
        return animalRepositorio.save(animal);
    }

    public Optional<Animal> buscarAnimalPorId(Long id){
        return animalRepositorio.findById(id);
    }

    public void eliminarAnimal(Long id){
        animalRepositorio.deleteById(id);
    }

    @Override
    public AnimalDomDto validarDatosAnimal(AnimalDomDto animalDomDto) throws NullPointerException{
        if(animalDomDto.getAnimalNombre().equals("") || animalDomDto.getRefugioId()>refugioServicio.listarRefugios().size()){
            throw new NullPointerException();
        }
        return animalDomDto;
    }

    public AnimalDto convertirADto(Animal animal){
        return modelMapper.map(animal ,AnimalDto.class);
    }

    @Override
    public Animal convertirAObjeto(AnimalDomDto animalDomDto) {
        Animal animal= new Animal();
        animal.setNombre(animalDomDto.getAnimalNombre());
        animal.setSexo(animalDomDto.getAnimalSexo());
        animal.setTalla(animalDomDto.getAnimalTalla());
        animal.setRaza(animalDomDto.getAnimalRaza());
        animal.setEdad(animalDomDto.getAnimalEdad());
        Refugio refugio=refugioRepositorio.findById(animalDomDto.getRefugioId()).orElse(null);
        List<Animal> listaAnimalesRefugio=refugio.getAnimales();
        listaAnimalesRefugio.add(animal);
        refugio.setAnimales(listaAnimalesRefugio);
        animal.setRefugio(refugio);
        return animal;
    }

    @Override
    public AnimalBasicoDto convertirADtoBasico(Animal animal) {
        return modelMapper.map(animal, AnimalBasicoDto.class);
    }


}
