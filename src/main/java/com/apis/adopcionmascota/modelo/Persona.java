package com.apis.adopcionmascota.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String direccion;
    private String telefono;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private List<Adopcion> adopciones;
}
