package com.apis.adopcionmascota.servicio.impl;

import com.apis.adopcionmascota.dto.RefugioBasicoDto;
import com.apis.adopcionmascota.modelo.Refugio;
import com.apis.adopcionmascota.repositorio.RefugioRepositorio;
import com.apis.adopcionmascota.servicio.IRefugioServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefugioServicio implements IRefugioServicio {

    @Autowired
    private RefugioRepositorio refugioRepositorio;
    @Autowired
    private ModelMapper modelMapper;

    public List<Refugio> listarRefugios(){ return refugioRepositorio.findAll(); }

    public Refugio guardarRefugio(Refugio refugio){
        return refugioRepositorio.save(refugio);
    }

    public Optional<Refugio> buscarRefugioPorId(Long id){
        return refugioRepositorio.findById(id);
    }

    public void eliminarRefugio(long id){
        refugioRepositorio.deleteById(id);
    }

    /**
     * Transformar un Refugio a RefugioBasicoDto, muestra el numero de animales de cada
     * refugio
     * @param refugio
     * @return
     */
    @Override
    public RefugioBasicoDto convertirADtoBasico(Refugio refugio) {
        RefugioBasicoDto refugioBasicoDto =modelMapper.map(refugio, RefugioBasicoDto.class);

        Refugio refugioBuscado=refugioRepositorio.findById(refugio.getId()).orElse(null);
        refugioBasicoDto.setNumeroAnimales(refugioBuscado.getAnimales().size());
        return refugioBasicoDto;
    }

    @Override
    public Refugio validarDatos(Refugio refugio) {
        if(refugio.getNombre().equals("")||refugio.getCiudad().equals("")||refugio.getDireccion().equals("")){
            return null;
        }
        return refugio;

    }
}
