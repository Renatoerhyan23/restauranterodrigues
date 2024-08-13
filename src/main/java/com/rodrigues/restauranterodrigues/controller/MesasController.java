package com.rodrigues.restauranterodrigues.controller;

import com.rodrigues.restauranterodrigues.model.Mesas;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MesasController {
        private List<Mesas> listaMesas = new ArrayList<>();

    public List<Mesas> getListaMesas() {
        return listaMesas;
    }

@GetMapping("/adicionar-mesa") 
public String recebeMesas(Model model){ 
    Mesas mesas = new Mesas();
    
    mesas.setId(listaMesas.size() + 1);
    mesas.setStatus("LIVRE");
    listaMesas.add(mesas);
    
    return "redirect:/cadastro-pedidos";  
    }

@GetMapping("/remover-mesa") 
public String deletaMesas(Model model){   
    int tamanho = listaMesas.size();
    if (tamanho > 0) { listaMesas.remove(tamanho-1); }    
    return "redirect:/cadastro-pedidos";  
    }

public void desocuparMesas(Integer idMesa) {
    for (Mesas m : listaMesas) {
        if (m.getId().equals(idMesa)) {
            m.setStatus("LIVRE");
            break;
        }
    }
}

public void ocuparMesas(Integer idMesas) {
    for (Mesas m : listaMesas) {
        if (m.getId().equals(idMesas)) {
            m.setStatus("OCUPADA");
        }
    }
}
    
@PostMapping("/atualizar-mesa")
public String atualizarMesa(@RequestParam("id") int id, @RequestParam("status") String status) {
        for (Mesas mesa : listaMesas) {
            if (mesa.getId() == id) {
                mesa.setStatus(status);
                break;
            }
        }
        return "redirect:/cadastro-mesas";
    }
}
