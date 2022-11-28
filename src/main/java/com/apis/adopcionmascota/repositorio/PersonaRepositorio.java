package com.apis.adopcionmascota.repositorio;

import com.apis.adopcionmascota.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepositorio extends JpaRepository<Persona, Long> {
}
