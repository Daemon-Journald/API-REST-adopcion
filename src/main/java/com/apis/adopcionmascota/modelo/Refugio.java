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
public class Refugio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private String ciudad;
    private String direccion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refugio")
    private List<Animal> animales;
}
