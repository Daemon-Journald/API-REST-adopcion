package com.apis.adopcionmascota.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
    @NonNull
    private String nombre;
    @NonNull
    private String email;
    @NonNull
    private String direccion;
    @NonNull
    private String telefono;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private List<Adopcion> adopciones;
}
