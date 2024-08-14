package com.rodrigues.restauranterodrigues.service;

import com.rodrigues.restauranterodrigues.data.PratosEntity;
import com.rodrigues.restauranterodrigues.data.PratosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PratosService {
   
    @Autowired
    PratosRepository pratosRepository;

public PratosEntity criarPratos(PratosEntity prat) {     
  prat.setId(null); 
  pratosRepository.save(prat); 
  return prat;
 } 

public List<PratosEntity> listarTodosPratos() { 
    return pratosRepository.findAll(); 
 } 
}
