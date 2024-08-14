package com.rodrigues.restauranterodrigues.controller;

import com.rodrigues.restauranterodrigues.data.PratosEntity;
import com.rodrigues.restauranterodrigues.service.PratosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PratosController {
    @Autowired
    private PratosService pratosService;
    
@GetMapping("/lista-pratos")
public String mostraListaPratos(Model model){
    model.addAttribute("pratos", new PratosEntity());
    model.addAttribute("listaPratos", pratosService.listarTodosPratos());
    return "lista-pratos";
}

public void cadastrarPrato(Integer idPedido, Integer idCardapio, int quantidade) {
   PratosEntity pr = new PratosEntity(quantidade, idPedido, idCardapio);
        pratosService.criarPratos(pr);
 }
}
