package com.zo.carros.api;

import com.zo.carros.domain.Carro;
import com.zo.carros.domain.CarroService;
import com.zo.carros.domain.dto.CarroDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/carros")
public class CarrosController {

  @Autowired
  private CarroService service;

  @GetMapping
  public ResponseEntity<Iterable<CarroDTO>> getCarros() {
    return ResponseEntity.ok(service.getCarros());
  }

  @GetMapping("/{id}")
  public ResponseEntity getById(@PathVariable("id") Long id) {
    CarroDTO c = service.getById(id);

    return ResponseEntity.ok(c);
  }

  @GetMapping("/tipo/{tipo}")
  public ResponseEntity getByTipo(@PathVariable("tipo") String tipo) {
    List<CarroDTO> c = service.getByTipo(tipo);

    return c.isEmpty() ?
           ResponseEntity.noContent().build() :
           ResponseEntity.ok(c);
  }

  @PostMapping
  public ResponseEntity addCarro(@RequestBody Carro carro) {
    CarroDTO carrin = service.addCarro(carro);

    return ResponseEntity.created(URI.create("http://localhost:8080/v1/carros/" + carrin.getId())).build();
  }

  @PutMapping("/{id}")
  public String updateCarro(@PathVariable("id") Long id, @RequestBody CarroDTO carro) {
    Carro updated = service.updateCarro(id, carro);

    return "Carro atualizado com sucesso: " + updated.getId();
  }

}
