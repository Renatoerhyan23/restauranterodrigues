package com.rodrigues.restauranterodrigues.controller;

import com.rodrigues.restauranterodrigues.data.CardapioEntity;
import com.rodrigues.restauranterodrigues.service.CardapioService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CardapioController {  
    
    @Autowired
    private CardapioService cardapioService;
        
@GetMapping("/cadastro-cardapio") 
public String mostraCardapio(Model model){ 
    model.addAttribute("cardapio", new CardapioEntity()); 
    model.addAttribute("listaCardapio", cardapioService.listarTodoCardapio()); 
    return "cadastro-cardapio"; 
    }

@PostMapping("/gravar-cardapio") 
public String recebeCardapio(Model model, @ModelAttribute CardapioEntity cardapio){ 
    cardapioService.criarCardapio(cardapio);
    return "redirect:/cadastro-cardapio";  
    }

@GetMapping("/excluir-cardapio")
public String excluirPrato(@RequestParam("id") Integer idCardapio) {
    cardapioService.deletarCardapio(idCardapio);
    return "redirect:/cadastro-cardapio";
 }
}
