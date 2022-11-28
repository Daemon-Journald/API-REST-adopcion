package com.apis.adopcionmascota.repositorio;

import com.apis.adopcionmascota.modelo.Refugio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefugioRepositorio extends JpaRepository<Refugio, Long> {
    Refugio findByNombre(String nombre);
}
