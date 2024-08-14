package com.rodrigues.restauranterodrigues.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity 
@Table(name="pratos")
public class PratosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="id_pedidos")
    private int idPedidos;
    
    @Column(name="cardapio_id")
    private Integer cardapioId;
    
    private int quantidade;
    
    public PratosEntity() {
    }
        
    public PratosEntity(Integer quantidade, Integer idPedidos, Integer cardapioId) {
        this.quantidade = quantidade;
        this.idPedidos = idPedidos;
        this.cardapioId = cardapioId;
    }
}
