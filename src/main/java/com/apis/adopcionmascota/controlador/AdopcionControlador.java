package com.apis.adopcionmascota.controlador;

import com.apis.adopcionmascota.dto.AdopcionBasicaDto;
import com.apis.adopcionmascota.dto.AdopcionDto;
import com.apis.adopcionmascota.dto.CrearAdopcionDto;
import com.apis.adopcionmascota.error.NotFoundException;
import com.apis.adopcionmascota.modelo.Adopcion;
import com.apis.adopcionmascota.servicio.impl.AdopcionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/adopcion")
public class AdopcionControlador {

    @Autowired
    private AdopcionServicio adopcionServicio;

    @GetMapping
    public ResponseEntity<?> listaAdopciones() {
        List<Adopcion> listaAdopcion = adopcionServicio.listarAdopciones();
        if (listaAdopcion.isEmpty()) {
            throw new NotFoundException();
        } else {
            List<AdopcionBasicaDto> listDto = listaAdopcion
                    .stream()
                    .map(adopcionServicio::convertirADtoBasico)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(listDto);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarAdopcionPorId(@PathVariable Long id) {
        Adopcion adopcion = adopcionServicio.buscarAdopcionPorId(id)
                .orElseThrow(()->new NotFoundException(id));
        AdopcionDto adopcionDto=adopcionServicio.convertirADto(adopcion);
        return ResponseEntity.ok(adopcionDto);
    }

    @PostMapping
    public ResponseEntity<?> crearAdopcion(@RequestBody CrearAdopcionDto crearAdopcionDto){
        Adopcion adopcionNueva=adopcionServicio.convetirAObjeto(crearAdopcionDto);
        if(adopcionNueva != null){
            AdopcionDto adopcionDto=adopcionServicio.convertirADto(adopcionServicio.guardarAdopcion(adopcionNueva));
            return ResponseEntity.status(HttpStatus.CREATED).body(adopcionDto);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAdopcionPorId(@PathVariable Long id) {
        Adopcion adopcionBusqueda = adopcionServicio.buscarAdopcionPorId(id)
                .orElseThrow(()->new NotFoundException(id));
        AdopcionDto adopcionDto=adopcionServicio.convertirADto(adopcionBusqueda);
        adopcionServicio.eliminarAdopcion(id);
        return ResponseEntity.ok(adopcionDto);

    }
}

