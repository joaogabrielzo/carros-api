package com.zo.carros;

import com.zo.carros.domain.*;
import com.zo.carros.domain.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
class CarrosApplicationTests {

	@Autowired
	CarroService service;

	@Test
	void testCRUD() {
		Carro carro = new Carro();
		carro.setNome("Monza");
		carro.setTipo("classicos");

		CarroDTO c = service.addCarro(carro);

		assertNotNull("O carro foi adicionado.", service.getById(c.getId()));
	}

	@Test
	void testByTipo() {

		List<CarroDTO> c = service.getByTipo("classicos");
		assertEquals("Lista de classicos tem 3 itens", 3, c.size());
	}

}
