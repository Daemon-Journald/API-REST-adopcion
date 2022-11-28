package com.apis.adopcionmascota.controlador;

import com.apis.adopcionmascota.dto.RefugioBasicoDto;
import com.apis.adopcionmascota.dto.RefugioDomDto;
import com.apis.adopcionmascota.modelo.Refugio;
import com.apis.adopcionmascota.servicio.RefugioServicio;
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
            return ResponseEntity.notFound().build();
        }else{
            List<RefugioBasicoDto> listDto=refugios
                    .stream()
                    .map(refugioServicio::convertirADtoBasico)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(listDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarRefugioPorId(@PathVariable Long id){
        Refugio refugio=refugioServicio.buscarRefugioPorId(id);
        if(refugio!= null){
            return ResponseEntity.ok(refugioServicio.convetirADto(refugio));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crearRefugio(@RequestBody RefugioDomDto refugioDomDto){
        Refugio refugioNuevo=refugioServicio.convertirARefugio(refugioDomDto);
        if(refugioNuevo != null){
            refugioServicio.guardarRefugio(refugioNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarRefugio(@PathVariable Long id){
        Refugio refugio=refugioServicio.buscarRefugioPorId(id);
        if(refugio != null){
            refugioServicio.eliminarRefugio(id);
            return ResponseEntity.ok(refugio);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
