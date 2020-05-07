package com.zo.carros.domain;

import javax.persistence.*;
import lombok.*;

@Entity(name = "carro")
@Data
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipo;

}
