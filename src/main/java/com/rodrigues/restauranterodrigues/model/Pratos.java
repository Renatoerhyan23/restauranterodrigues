package com.rodrigues.restauranterodrigues.model;

import lombok.Data;

@Data
public class Pratos {
    private Integer id, quantidade, idPedidos, cardapioId;
    
    public Pratos() {
    }
        
    public Pratos(Integer quantidade, Integer idPedidos, Integer cardapioId) {
        this.quantidade = quantidade;
        this.idPedidos = idPedidos;
        this.cardapioId = cardapioId;
    }


}
