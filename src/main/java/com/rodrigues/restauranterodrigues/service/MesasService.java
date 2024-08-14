package com.rodrigues.restauranterodrigues.service;

import com.rodrigues.restauranterodrigues.data.MesasEntity;
import com.rodrigues.restauranterodrigues.data.MesasRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MesasService {
    
@Autowired 
MesasRepository mesasRepository;

public MesasEntity criarMesa(MesasEntity mesa) { 

  mesa.setId(null); 
  mesasRepository.save(mesa); 

  return mesa;
 } 

public MesasEntity atualizarMesa(Integer mesaId, MesasEntity mesasRequest) { 
    MesasEntity mesa = buscarPorId(mesaId); 
    
    mesa.setStatus(mesasRequest.getStatus()); 
    mesasRepository.save(mesa); 

    return mesa; 
 } 

public List<MesasEntity> listarTodasMesas() { 

    return mesasRepository.findAll();

 } 

public MesasEntity buscarPorId(Integer id) {
     return mesasRepository.findById(id).orElseThrow();
    }
    
public void deletarMesa() { 
    if (mesasRepository.findTopByOrderByIdDesc().getStatus().equals("DISPONIVEL")) 
    { mesasRepository.delete(mesasRepository.findTopByOrderByIdDesc()); }
 } 
}
