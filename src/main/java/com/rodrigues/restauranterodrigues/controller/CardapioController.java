package com.rodrigues.restauranterodrigues.controller;

import com.rodrigues.restauranterodrigues.model.Cardapio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CardapioController {
    private List<Cardapio> listaCardapio = new ArrayList<>();    

    public List<Cardapio> getListaCardapio() {
        return listaCardapio;
    }
    
@GetMapping("/cadastro-cardapio") 
public String mostraCardapio(Model model){ 
    model.addAttribute("cardapio", new Cardapio()); 
    model.addAttribute("listaCardapio", listaCardapio); 
    return "cadastro-cardapio"; 
    }

@PostMapping("/gravar-cardapio") 
public String recebeCardapio(Model model, @ModelAttribute Cardapio cardapio){ 
    cardapio.setId(listaCardapio.size() + 1);
    listaCardapio.add(cardapio);
    model.addAttribute("cardapio", cardapio);
    return "redirect:/cadastro-cardapio";  
    }

@GetMapping("/excluir-cardapio")
public String excluirPrato(@RequestParam("id") Integer idCardapio) {
    listaCardapio.removeIf(c -> c.getId().equals(idCardapio));
    return "redirect:/cadastro-cardapio";
 }
}
