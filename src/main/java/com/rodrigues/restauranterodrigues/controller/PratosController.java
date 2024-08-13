package com.rodrigues.restauranterodrigues.controller;

import com.rodrigues.restauranterodrigues.model.Pratos;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PratosController {
    private List<Pratos> listaPratos = new ArrayList<>();
    
@GetMapping("/lista-pratos")
public String mostraListaPratos(Model model){
    model.addAttribute("pratos", new Pratos());
    model.addAttribute("listaPratos", listaPratos);
    return "lista-pratos";
}

public void cadastrarPrato(Integer idPedido, Integer idCardapio) {
   Pratos pr = new Pratos(1, idPedido, idCardapio);
        pr.setId(listaPratos.size() + 1);
        listaPratos.add(pr);
 }
}
