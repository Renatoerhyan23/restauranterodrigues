package com.rodrigues.restauranterodrigues.service;

import com.rodrigues.restauranterodrigues.data.CardapioEntity;
import com.rodrigues.restauranterodrigues.data.CardapioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardapioService {
        
  @Autowired 
  CardapioRepository cardapioRepository;

public CardapioEntity criarCardapio(CardapioEntity carda) { 

    carda.setId(null); 

  cardapioRepository.save(carda); 

  return carda;
 } 

public CardapioEntity atualizarCardapio(Integer cardaId, CardapioEntity cardapioRequest) { 
    CardapioEntity carda = buscarPorId(cardaId); 
    
    carda.setNomePrato(cardapioRequest.getNomePrato()); 
    carda.setDescricao(cardapioRequest.getDescricao()); 
    carda.setPreco(cardapioRequest.getPreco()); 

    cardapioRepository.save(carda); 

    return carda; 
 } 

public List<CardapioEntity> listarTodoCardapio() { 

    return cardapioRepository.findAll(); 

 } 

public CardapioEntity buscarPorId(Integer id) {
     return cardapioRepository.findById(id).orElseThrow();
    }
    
public void deletarCardapio(Integer cardaId) { 

    CardapioEntity carda = buscarPorId(cardaId); 

    cardapioRepository.deleteById(carda.getId()); 
 } 
}
