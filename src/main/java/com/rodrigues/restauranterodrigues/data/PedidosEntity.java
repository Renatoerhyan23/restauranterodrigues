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
@Table(name="pedidos")
public class PedidosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="cpf_cliente")
    private String cpfCliente;
    
    @Column(name="mesas_id")
    private Integer mesasId;
    
    @Column(name="valor_total")
    private double valorTotal;  
    
    private String status;  
}
