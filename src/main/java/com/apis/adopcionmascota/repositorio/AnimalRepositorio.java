package com.apis.adopcionmascota.repositorio;

import com.apis.adopcionmascota.modelo.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepositorio extends JpaRepository<Animal, Long> {
}
