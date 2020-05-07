package com.zo.carros.domain.dto;

import com.zo.carros.domain.Carro;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CarroDTO extends Carro{
    private Long id;
    private String nome;
    private String tipo;

    public static CarroDTO create(Carro c) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(c, CarroDTO.class);
    }
}
