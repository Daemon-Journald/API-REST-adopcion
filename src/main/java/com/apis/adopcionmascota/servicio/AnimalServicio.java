package com.apis.adopcionmascota.servicio;

import com.apis.adopcionmascota.dto.AnimalBasicoDto;
import com.apis.adopcionmascota.dto.AnimalDomDto;
import com.apis.adopcionmascota.dto.AnimalDto;
import com.apis.adopcionmascota.modelo.Animal;
import com.apis.adopcionmascota.modelo.Refugio;
import com.apis.adopcionmascota.repositorio.AnimalRepositorio;
import com.apis.adopcionmascota.repositorio.RefugioRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServicio implements IAnimalServicio{
    @Autowired
    private AnimalRepositorio animalRepositorio;

    @Autowired
    private RefugioRepositorio refugioRepositorio;


    @Autowired
    private ModelMapper modelMapper;

    public List<Animal> listarAnimales(){ return animalRepositorio.findAll(); }

    public Animal guardarAnimales(Animal animal){
        return animalRepositorio.save(animal);
    }

    public Animal buscarAnimalPorId(Long id){
        return animalRepositorio.findById(id).orElse(null);
    }

    public void eliminarAnimales(long id){
        animalRepositorio.deleteById(id);
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
