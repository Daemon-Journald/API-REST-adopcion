package com.apis.adopcionmascota.servicio;

import com.apis.adopcionmascota.dto.AnimalBasicoDto;
import com.apis.adopcionmascota.dto.AnimalDomDto;
import com.apis.adopcionmascota.dto.AnimalDto;
import com.apis.adopcionmascota.modelo.Animal;

import java.util.List;

public interface IAnimalServicio {

    List<Animal> listarAnimales();

    Animal guardarAnimales(Animal animal);

    Animal buscarAnimalPorId(Long id);

    void eliminarAnimales(long id);

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
