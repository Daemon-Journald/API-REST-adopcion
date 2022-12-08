package com.apis.adopcionmascota.servicio;

import com.apis.adopcionmascota.dto.AnimalBasicoDto;
import com.apis.adopcionmascota.modelo.Animal;

import java.util.List;
import java.util.Optional;

public interface IAnimalServicio {

    List<Animal> listarAnimalesPorRefugio(Long idRefugio);

    Animal guardarAnimalEnRefugio(Animal animal, Long idRefugio);

    Animal buscarAnimalIdPorRefugio(Long idRefugio, Long idAnimal);

    Optional<Animal> buscarAnimalPorId(Long idAnimal);

    void eliminarAnimal(Long id);

    /**
     * Conversion de un Objeto Animal a AnimalDto
     * @param animal
     * @return AnimalDto
     */
    AnimalBasicoDto convertirADtoBasico(Animal animal);

    Animal validarAnimal(Animal animal);

    List<Animal> listarAnimalesPorEstado(String estado);

    List<Animal> filtrarAnimalesPorEstado(String estado, List<Animal> animales);


}
