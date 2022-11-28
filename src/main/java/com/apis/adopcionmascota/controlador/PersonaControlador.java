package com.apis.adopcionmascota.controlador;

import com.apis.adopcionmascota.dto.PersonaBasicaDto;
import com.apis.adopcionmascota.dto.PersonaDomDto;
import com.apis.adopcionmascota.modelo.Persona;
import com.apis.adopcionmascota.servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
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
            return ResponseEntity.notFound().build();
        } else {
            List<PersonaBasicaDto> listDto = personas
                    .stream()
                    .map(personaServicio::convertirADtoBasico)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(listDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPersona(@PathVariable Long id) {
        Persona persona = personaServicio.buscarPersonaPorId(id);
        if (persona == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(personaServicio.convertirADto(persona));
        }
    }

    @PostMapping
    public ResponseEntity<?> crearPersona(@RequestBody PersonaDomDto personaDomDto) {
        Persona persona=personaServicio.convertirAPersona(personaDomDto);
        if(persona != null){
            personaServicio.guardarPersona(persona);
            return ResponseEntity.ok(persona);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
        Persona persona = personaServicio.buscarPersonaPorId(id);
        if (persona == null) {
            return ResponseEntity.notFound().build();
        } else {
            personaServicio.eliminarPersona(id);
            return ResponseEntity.ok(persona);
        }
    }

}
