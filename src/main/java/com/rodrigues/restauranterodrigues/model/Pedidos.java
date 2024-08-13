package com.rodrigues.restauranterodrigues.model;

import lombok.Data;

@Data
public class Pedidos {
    private Integer id;
    private String cpfCliente, status;
    private Integer mesasId;
    private Integer valorTotal;    
}
