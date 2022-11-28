package com.apis.adopcionmascota.repositorio;

import com.apis.adopcionmascota.modelo.Adopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdopcionRespositorio extends JpaRepository<Adopcion, Long> {
}
