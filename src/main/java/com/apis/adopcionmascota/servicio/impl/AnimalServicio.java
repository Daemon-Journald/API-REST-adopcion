package com.apis.adopcionmascota.servicio.impl;

import com.apis.adopcionmascota.dto.AnimalBasicoDto;
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

    public List<Animal> listarAnimalesPorRefugio(Long idRefugio){
        Refugio refugio=refugioServicio.buscarRefugioPorId(idRefugio).orElse(null);
        if(refugio == null){
            return null;
        }
        return refugio.getAnimales();
    }

    public Animal guardarAnimalEnRefugio(Animal animal, Long idRefugio){
        Animal animalNuevo=animalRepositorio.save(animal);
        Refugio refugio=refugioServicio.buscarRefugioPorId(idRefugio).orElse(null);
        List<Animal> listaAnimalesPorRefugio=refugio.getAnimales();
        listaAnimalesPorRefugio.add(animalNuevo);
        refugio.setAnimales(listaAnimalesPorRefugio);
        refugioServicio.guardarRefugio(refugio);
        return animalNuevo;

    }

    public Animal buscarAnimalIdPorRefugio(Long idRefugio, Long idAnimal){
        Refugio refugio=refugioServicio.buscarRefugioPorId(idRefugio).orElse(null);
        if(refugio == null) return null;
        List<Animal> listaAnimalesPorRefugio=refugio.getAnimales();
        Animal animalEncontrado=null;
        for (Animal animal:listaAnimalesPorRefugio) {
            if(animal.getId() == idAnimal){
                animalEncontrado=animal;
                break;
            }
        }
        return animalEncontrado;

    }

    @Override
    public Optional<Animal> buscarAnimalPorId(Long idAnimal) {
        return animalRepositorio.findById(idAnimal);
    }


    public void eliminarAnimal(Long id){
        animalRepositorio.deleteById(id);
    }

    public AnimalDto convertirADto(Animal animal){
        return modelMapper.map(animal ,AnimalDto.class);
    }
    @Override
    public AnimalBasicoDto convertirADtoBasico(Animal animal) {
        return modelMapper.map(animal, AnimalBasicoDto.class);
    }

    @Override
    public Animal validarAnimal(Animal animal) {
        if (animal.getNombre().equals("")||animal.getNombre() == null) {
            return null;
        }
        return animal;
    }


}
