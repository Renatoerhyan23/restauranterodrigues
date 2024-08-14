package com.rodrigues.restauranterodrigues.controller;

import com.rodrigues.restauranterodrigues.data.MesasEntity;
import com.rodrigues.restauranterodrigues.service.MesasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MesasController {
     
    @Autowired
    private MesasService mesasService;
    
@GetMapping("/adicionar-mesa") 
public String recebeMesas(Model model){ 
    MesasEntity mesas = new MesasEntity();    
    mesas.setStatus("DISPONIVEL");    
    mesasService.criarMesa(mesas);
    
    return "redirect:/cadastro-pedidos";  
    }

@GetMapping("/remover-mesa") 
public String deletaMesas(Model model){   
    mesasService.deletarMesa();
    return "redirect:/cadastro-pedidos";  
    }

public void desocuparMesas(Integer idMesa) {
        MesasEntity m = new MesasEntity();
        m.setStatus("DISPONIVEL");
        mesasService.atualizarMesa(idMesa, m);
}

public void ocuparMesas(Integer idMesa) {
        MesasEntity m = new MesasEntity();
        m.setStatus("OCUPADA");
        mesasService.atualizarMesa(idMesa, m);
 }
}
