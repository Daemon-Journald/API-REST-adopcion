package com.apis.adopcionmascota.modelo;

import com.apis.adopcionmascota.modelo.enums.AdopcionEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private char sexo;
    private Integer edad;
    private Long talla;
    private String raza;
    private String estado=AdopcionEnum.NO_ADOPTADO.toString();
}
