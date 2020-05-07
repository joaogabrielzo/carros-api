package com.zo.carros.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> { // JpaRepository<Objeto, Tipo do ID>
    List<Carro> findByTipo(String tipo);

}
