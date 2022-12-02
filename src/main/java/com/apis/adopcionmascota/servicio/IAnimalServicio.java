package com.apis.adopcionmascota.servicio;

import com.apis.adopcionmascota.dto.AnimalBasicoDto;
import com.apis.adopcionmascota.dto.AnimalDomDto;
import com.apis.adopcionmascota.dto.AnimalDto;
import com.apis.adopcionmascota.modelo.Animal;

import java.util.List;
import java.util.Optional;

public interface IAnimalServicio {

    List<Animal> listarAnimales();

    Animal guardarAnimales(Animal animal);

    Optional<Animal> buscarAnimalPorId(Long id);

    void eliminarAnimal(Long id);

    AnimalDomDto validarDatosAnimal(AnimalDomDto animalDomDto);

    /**
     * Conversion de un Objeto Animal a AnimalDto
     * @param animal
     * @return AnimalDto
     */
    AnimalDto convertirADto(Animal animal);

    /**
     * Conversion de AnimalDto a Animal
     * @param  animalDomDto
     * @return Animal
     */
    Animal convertirAObjeto(AnimalDomDto animalDomDto);

    AnimalBasicoDto convertirADtoBasico(Animal animal);


}
