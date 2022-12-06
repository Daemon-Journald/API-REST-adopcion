package com.apis.adopcionmascota.controlador;

import com.apis.adopcionmascota.dto.RefugioBasicoDto;
import com.apis.adopcionmascota.error.NotFoundException;
import com.apis.adopcionmascota.modelo.Refugio;
import com.apis.adopcionmascota.servicio.impl.RefugioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/refugio")
public class RefugioControlador {

    @Autowired
    private RefugioServicio refugioServicio;

    @GetMapping
    public ResponseEntity<?> listarRefugios(){
        List<Refugio> refugios=refugioServicio.listarRefugios();
        if (refugios.isEmpty()){
            throw new NotFoundException();
        }else{
            List<RefugioBasicoDto> listDto=refugios
                    .stream()
                    .map(refugioServicio::convertirADtoBasico)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(listDto);
        }
    }

    @GetMapping("/{id}")
    public Refugio buscarRefugioPorId(@PathVariable Long id){
        Refugio refugio=refugioServicio.buscarRefugioPorId(id)
                .orElseThrow(()->new NotFoundException(id));
        return refugio;
    }

    //falta revisar
    @PostMapping
    public ResponseEntity<?> crearRefugio(@RequestBody Refugio refugioNuevo){
        Refugio refugio=refugioServicio.validarDatos(refugioNuevo);
        if(refugioNuevo == null){
            return ResponseEntity.badRequest().build();
        }
        refugioServicio.guardarRefugio(refugio);
        return ResponseEntity.status(HttpStatus.CREATED).body(refugio);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRefugio(@PathVariable Long id){
        Refugio refugio=refugioServicio.buscarRefugioPorId(id)
                .orElseThrow(()->new NotFoundException(id));
        refugioServicio.eliminarRefugio(id);
        return ResponseEntity.ok(refugio);
    }
}
