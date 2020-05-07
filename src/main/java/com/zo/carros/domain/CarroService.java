package com.zo.carros.domain;

import com.zo.carros.domain.dto.CarroDTO;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

  @Autowired
  private CarroRepository repo;

  public List<CarroDTO> getCarros() {
    List<Carro> carros = repo.findAll();

    List<CarroDTO> list = carros.stream().map(CarroDTO::create).collect(Collectors.toList());
    return list;
  }

  public CarroDTO getById(Long id) {
    Optional<Carro> c = repo.findById(id);
    return c.map(CarroDTO::create)
            .orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado.", "Não encontrado."));
  }

  public List<CarroDTO> getByTipo(String tipo) {
    List<Carro> c = repo.findByTipo(tipo);

    return c.stream().map(CarroDTO::create).collect(Collectors.toList());
  }

  public CarroDTO addCarro(Carro carro) {
    Assert.isNull(carro.getId(), "Não foi possível inserir o registro.");

    return CarroDTO.create(repo.save(carro));
  }

  public Carro updateCarro(Long id, Carro carro) {
    CarroDTO c = getById(id);

    Carro db = c;
    db.setNome(carro.getNome());
    db.setTipo(carro.getTipo());
    repo.save(db);

    return db;

  }

}
