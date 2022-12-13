package com.apis.adopcionmascota.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Refugio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String ciudad;
    @Column(nullable = false)
    private String direccion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Animal> animales=new ArrayList<>();
}
