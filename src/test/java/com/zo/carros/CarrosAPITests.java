package com.zo.carros;

import com.zo.carros.domain.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosAPITests {
  @Autowired
  protected TestRestTemplate rest;

  private ResponseEntity<CarroDTO> getCarro(String url) {
    return rest.getForEntity(url, CarroDTO.class);
  }

  private ResponseEntity<List<CarroDTO>> getCarros(String url) {
    return rest.exchange(
      url,
      HttpMethod.GET,
      null,
      new ParameterizedTypeReference<List<CarroDTO>>() {
      });
  }

  @Test
  void testList() {
    List<CarroDTO> carros = getCarros("/v1/carros").getBody();
    assertNotNull("A tabela de carros não deve estar vazia", carros);
    assertEquals("A tabela de carros deve conter 2 elementos", 2, carros.size());
  }

  @Test
  void testTipo() {
    List<CarroDTO> carros = getCarros("/v1/carros/tipo/classicos").getBody();
    assertNotNull("Deve existir carros com o tipo 'classicos'", carros);
    assertEquals("Deve existir 2 carros classicos", 2, carros.size());
  }

  @Test
  void testGetNotFound() {
    HttpStatus response = getCarro("/v1/carros/999").getStatusCode();
    assertEquals("Não deve existir carro com id 999", response, HttpStatus.NOT_FOUND);
  }

  @Test
  void testNoContent() {
    HttpStatus response = getCarro("/v1/carros/tipo/esportivos").getStatusCode();
    assertEquals("Não deve existir carros esportivos", response, HttpStatus.NO_CONTENT);
  }

  @Test
  void testById() {
    ResponseEntity<CarroDTO> response = getCarro("/v1/carros/1");
    assertEquals("Request deu certo", response.getStatusCode(), HttpStatus.OK);

    CarroDTO c = response.getBody();
    assertEquals("O carro é um fusca", "Fusca", c.getNome());
  }

}
