package com.rodrigues.restauranterodrigues.controller;

import com.rodrigues.restauranterodrigues.model.Cardapio;
import com.rodrigues.restauranterodrigues.model.Mesas;
import com.rodrigues.restauranterodrigues.model.Pedidos;
import com.rodrigues.restauranterodrigues.model.Pratos;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PedidosController {
        private List<Pedidos> listaPedidos = new ArrayList<>();        
        
        @Autowired
        private MesasController mesasController;
        
        @Autowired
        private CardapioController cardapioController;
        
        @Autowired
        private PratosController pratosController;
        
@GetMapping("/")
public String inicio() {
    return "index";
    }
    
@GetMapping("/lista-pedidos") 
public String mostraPedidos(Model model){ 
    model.addAttribute("pedidos", new Pedidos()); 
    model.addAttribute("listaPedidos", listaPedidos); 
    return "lista-pedidos"; 
    }

@GetMapping("/adicionar-pedido") 
public String recebePedidos(Model model, @ModelAttribute Pedidos pedidos, @RequestParam("mesaId") String mesa){ 
    pedidos.setId(listaPedidos.size() + 1);
       
    Integer idMesa = Integer.parseInt(mesa);
    mesasController.ocuparMesas(idMesa);
    
    pedidos.setMesasId(idMesa);    
    pedidos.setValorTotal(0);      
    pedidos.setStatus("ABERTO");
    
    listaPedidos.add(pedidos);
    model.addAttribute("pedidos", pedidos);
    return "redirect:/cadastro-pedidos";  
    }

private Pedidos buscarPedido(Integer idPedido) {
    for (Pedidos p : listaPedidos) {
        if (p.getId().equals(idPedido)) {
            return p;
        }
    }
    return null;
}
    
@GetMapping("/cadastro-pedidos") 
public String mostraMesas(Model model){    
    model.addAttribute("mesas", new Mesas()); 
    model.addAttribute("listaMesas", mesasController.getListaMesas()); 
    model.addAttribute("listaPedidos", listaPedidos);
    return "cadastro-pedidos"; 
    }

@GetMapping("/alterar-pedido")
public String carregaPagina(Model model, @RequestParam("id") String id) {
    model.addAttribute("pedidoId", id);
    model.addAttribute("cardapio", new Cardapio()); 
    model.addAttribute("listaPrato", cardapioController.getListaCardapio()); 
    return "atualizar-pedido";      
    }

@GetMapping("/atualizar-pedidos")
public String atualizarPedido(Model model, @RequestParam("id") String id, 
                                           @RequestParam("idCardapio") String cardapioId, 
                                           @RequestParam("preco") Integer preco) {
    Integer idPedido = Integer.parseInt(id);     
    Integer idCardapio = Integer.parseInt(cardapioId);
    Pedidos p = buscarPedido(idPedido); 
    
    pratosController.cadastrarPrato(idPedido, idCardapio);
       
    p.setValorTotal(p.getValorTotal() + preco);
    
    return "redirect:/cadastro-pedidos";       
    }

@GetMapping("/finalizar-pedido")
    public String finalizarPedido (@RequestParam("id") String id, @RequestParam("mesaId") String idMesa) {
      Integer idPedido = Integer.parseInt(id);
      Integer mesa = Integer.parseInt(idMesa);
      Pedidos p = buscarPedido(idPedido);
      
      p.setStatus("FINALIZADO"); 
      p.setCpfCliente("");
      mesasController.desocuparMesas(mesa);
      
      return "redirect:/cadastro-pedidos";
    }
}
