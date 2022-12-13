package com.apis.adopcionmascota.controlador;

import com.apis.adopcionmascota.dto.PersonaBasicaDto;
import com.apis.adopcionmascota.dto.PersonaDto;
import com.apis.adopcionmascota.error.NotFoundException;
import com.apis.adopcionmascota.modelo.Persona;
import com.apis.adopcionmascota.servicio.impl.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persona")
public class PersonaControlador {

    @Autowired
    private PersonaServicio personaServicio;

    @GetMapping
    public ResponseEntity<?> listarClientes() {
        List<Persona> personas = personaServicio.listarPersonas();
        if (personas.isEmpty()) {
           throw new NotFoundException();
        } else {
            List<PersonaBasicaDto> listDto = personas
                    .stream()
                    .map(personaServicio::convertirADtoBasico)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(listDto);
        }
    }

    @GetMapping("/{id}")
    public PersonaDto buscarPersona(@PathVariable Long id) {
        Persona persona = personaServicio.buscarPersonaPorId(id)
                .orElseThrow(()->new NotFoundException(id));
        return personaServicio.convertirADto(persona);
    }

    @PostMapping
    public ResponseEntity<?> crearPersona(@RequestBody Persona persona){
        Persona personaValidada=personaServicio.validarDatosPersona(persona);
        personaServicio.guardarPersona(personaValidada);
        return ResponseEntity.status(HttpStatus.CREATED).body(personaValidada);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable Long id) {
        Persona persona = personaServicio.buscarPersonaPorId(id)
                .orElseThrow(()->new NotFoundException(id));
        personaServicio.eliminarPersona(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}


