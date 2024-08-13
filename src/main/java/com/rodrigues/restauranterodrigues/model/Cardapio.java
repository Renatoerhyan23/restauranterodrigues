package com.rodrigues.restauranterodrigues.model;

import lombok.Data;

@Data
public class Cardapio {
    private Integer id;
    private String nomePrato, descricao;
    private Integer preco;
}