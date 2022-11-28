package com.apis.adopcionmascota.servicio;

import com.apis.adopcionmascota.dto.RefugioBasicoDto;
import com.apis.adopcionmascota.dto.RefugioDomDto;
import com.apis.adopcionmascota.dto.RefugioDto;
import com.apis.adopcionmascota.modelo.Refugio;
import com.apis.adopcionmascota.repositorio.RefugioRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RefugioServicio implements IRefugioServicio{

    @Autowired
    private RefugioRepositorio refugioRepositorio;
    @Autowired
    private ModelMapper modelMapper;

    public List<Refugio> listarRefugios(){ return refugioRepositorio.findAll(); }

    public Refugio guardarRefugio(Refugio refugio){
        return refugioRepositorio.save(refugio);
    }

    public Refugio buscarRefugioPorId(Long id){
        return refugioRepositorio.findById(id).orElse(null);
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
    public RefugioDto convetirADto(Refugio refugio) {
        return modelMapper.map(refugio, RefugioDto.class);
    }

    @Override
    public Refugio convertirARefugio(RefugioDomDto refugioDomDto) {
        Refugio refugioNuevo=modelMapper.map(refugioDomDto, Refugio.class);
        refugioNuevo.setAnimales(new ArrayList<>());
        return refugioNuevo;
    }


}
