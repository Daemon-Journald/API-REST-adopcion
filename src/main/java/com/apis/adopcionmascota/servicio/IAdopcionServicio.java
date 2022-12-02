package com.apis.adopcionmascota.servicio;

import com.apis.adopcionmascota.dto.AdopcionBasicaDto;
import com.apis.adopcionmascota.dto.AdopcionDomDto;
import com.apis.adopcionmascota.dto.AdopcionDto;
import com.apis.adopcionmascota.modelo.Adopcion;

import java.util.List;
import java.util.Optional;

public interface IAdopcionServicio {


    /**
     * Conversion de un objeto a un DTO mediante Model Mapper
     * @param adopcion
     * @return
     */
    AdopcionBasicaDto convertirDto(Adopcion adopcion);

    List<Adopcion> listarAdopciones();

    Adopcion guardarAdopcion(Adopcion adopcion);

    Optional<Adopcion> buscarAdopcionPorId(Long id);

    void eliminarAdopcion(long id);

    AdopcionBasicaDto convertirADtoBasico(Adopcion adopcion);

    AdopcionDto convertirADto(Adopcion adopcion);

    Adopcion convetirAObjeto(AdopcionDomDto adopcionDomDto);

}
