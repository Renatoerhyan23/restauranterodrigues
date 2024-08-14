package com.rodrigues.restauranterodrigues.service;

import com.rodrigues.restauranterodrigues.data.PedidosEntity;
import com.rodrigues.restauranterodrigues.data.PedidosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidosService {
            
  @Autowired 
  PedidosRepository pedidosRepository;

public PedidosEntity criarPedido(PedidosEntity pedi) { 

    pedi.setId(null); 

  pedidosRepository.save(pedi); 

  return pedi;
 } 

public PedidosEntity atualizarPedidos(Integer pediId, PedidosEntity pedidosRequest) { 
    PedidosEntity pedi = buscarPorId(pediId); 
    
    pedi.setCpfCliente(pedidosRequest.getCpfCliente()); 
    pedi.setStatus(pedidosRequest.getStatus()); 
    pedi.setMesasId(pedidosRequest.getMesasId()); 
    pedi.setValorTotal(pedidosRequest.getValorTotal());

    pedidosRepository.save(pedi); 

    return pedi; 
 } 

public List<PedidosEntity> listarTodosPedidos() { 

    return pedidosRepository.findAll(); 

 } 

public PedidosEntity buscarPorId(Integer id) {
     return pedidosRepository.findById(id).orElseThrow();
    }
    
public void deletarPedidos(Integer pediId) { 

    PedidosEntity pedi = buscarPorId(pediId); 

    pedidosRepository.deleteById(pedi.getId()); 
 }
}
