package com.apis.adopcionmascota.controlador;

import com.apis.adopcionmascota.dto.AnimalBasicoDto;
import com.apis.adopcionmascota.error.BadRequestException;
import com.apis.adopcionmascota.error.NotFoundException;
import com.apis.adopcionmascota.modelo.Animal;
import com.apis.adopcionmascota.servicio.impl.AnimalServicio;
import com.apis.adopcionmascota.servicio.impl.RefugioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AnimalControlador {

    @Autowired
    private AnimalServicio animalServicio;

    @Autowired
    private RefugioServicio refugioServicio;

    /**
     * Listar animales
     * @return listDto
     */
    @GetMapping("/refugio/{idRefugio}/animal")
    public ResponseEntity<?> listarAnimales(@PathVariable Long idRefugio){
        List<Animal> animalList=animalServicio.listarAnimalesPorRefugio(idRefugio);
        if (animalList.isEmpty()){
            throw new NotFoundException();
        }else{
            List<AnimalBasicoDto> listDto=animalList
                    .stream()
                    .map(animalServicio ::convertirADtoBasico)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(listDto);
        }
    }

    /**
     * Buscar Animal por id
     * @param idAnimal
     * @return Animal
     * los parametros deben estar en orden al igual que esta en la ruta y tambien
     * en los metodos
     */
    @GetMapping("/refugio/{idRefugio}/animal/{idAnimal}")
    public Animal bucarAnimal(@PathVariable Long idRefugio, @PathVariable Long idAnimal){
        Animal animal=animalServicio.buscarAnimalIdPorRefugio(idRefugio, idAnimal);
        if(animal == null){
            throw new NotFoundException(idAnimal);
        }
        return animal;
    }

    /**
     * Crear animal
     * @param
     * @return
     */
   @PostMapping("/refugio/{idRefugio}/animal")
    public ResponseEntity<?> crearAnimal(@PathVariable Long idRefugio,@RequestBody Animal animal) {
       Animal animalValidado=animalServicio.validarAnimal(animal);
       if(animalValidado == null){
           throw new BadRequestException(animalValidado);
       }
       animalServicio.guardarAnimalEnRefugio(animal,idRefugio);
       return ResponseEntity.ok(animalValidado);

   }
}