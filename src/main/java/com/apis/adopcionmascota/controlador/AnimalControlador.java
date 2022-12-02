package com.apis.adopcionmascota.controlador;

import com.apis.adopcionmascota.dto.AnimalBasicoDto;
import com.apis.adopcionmascota.dto.AnimalDomDto;
import com.apis.adopcionmascota.dto.AnimalDto;
import com.apis.adopcionmascota.dto.RefugioBasicoDto;
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
@RequestMapping("/animal")
public class AnimalControlador {

    @Autowired
    private AnimalServicio animalServicio;

    @Autowired
    private RefugioServicio refugioServicio;

    /**
     * Listar animales
     * @return listDto
     */
    @GetMapping
    public ResponseEntity<?> listarAnimales(){
        List<Animal> animalList=animalServicio.listarAnimales();
        if (animalList.isEmpty()){
            return ResponseEntity.notFound().build();
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
     */
    @GetMapping("/{idAnimal}")
    public ResponseEntity<?> bucarAnimal(@PathVariable Long idAnimal){
        Animal animal=animalServicio.buscarAnimalPorId(idAnimal)
                .orElseThrow(()->new NotFoundException(idAnimal));
        AnimalDto animalDto=animalServicio.convertirADto(animal);
        RefugioBasicoDto refugioBasicoDto=refugioServicio.convertirADtoBasico(animal.getRefugio());
        animalDto.setAnimalRefugio(refugioBasicoDto);
        return ResponseEntity.ok(animalDto);
    }

    /**
     * Crear animal
     * @param animalDomDto
     * @return
     */
   @PostMapping
    public ResponseEntity<?> crearAnimal(@RequestBody AnimalDomDto animalDomDto) {
       Animal animalNuevo=animalServicio.convertirAObjeto(animalDomDto);
       if(animalDomDto.getAnimalNombre().equals("") || animalDomDto.getRefugioId() > refugioServicio.listarRefugios().size()){
           throw new BadRequestException(animalNuevo);
       }
       animalServicio.guardarAnimales(animalNuevo);
       AnimalDto animalDto=animalServicio.convertirADto(animalNuevo);
       RefugioBasicoDto refugioBasicoDto=refugioServicio.convertirADtoBasico(animalNuevo.getRefugio());
       animalDto.setAnimalRefugio(refugioBasicoDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(animalDto);
   }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAnimal(@PathVariable Long id) {
        Animal animal = animalServicio.buscarAnimalPorId(id)
                .orElseThrow(()->new NotFoundException(id));
        animalServicio.eliminarAnimal(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(animal);

    }
}