package com.apis.adopcionmascota.repositorio;

import com.apis.adopcionmascota.modelo.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepositorio extends JpaRepository<Animal, Long> {
    List<Animal> findByEstado(String estado);
}
