package com.rodrigues.restauranterodrigues.controller;

import com.rodrigues.restauranterodrigues.data.CardapioEntity;
import com.rodrigues.restauranterodrigues.data.MesasEntity;
import com.rodrigues.restauranterodrigues.data.PedidosEntity;
import com.rodrigues.restauranterodrigues.service.CardapioService;
import com.rodrigues.restauranterodrigues.service.MesasService;
import com.rodrigues.restauranterodrigues.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PedidosController {      
        @Autowired
        private PedidosService pedidosService;
            
        @Autowired
        private MesasService mesasService;
        
        @Autowired
        private MesasController mesasController;
        
        @Autowired
        private CardapioController cardapioController;
        
        @Autowired
        private CardapioService cardapioService;
        
        @Autowired
        private PratosController pratosController;
        
@GetMapping("/")
public String inicio() {
    return "index";
    }
    
@GetMapping("/lista-pedidos") 
public String mostraPedidos(Model model){ 
    model.addAttribute("pedidos", new PedidosEntity()); 
    model.addAttribute("listaPedidos", pedidosService.listarTodosPedidos()); 
    return "lista-pedidos"; 
    }

@GetMapping("/adicionar-pedido") 
public String recebePedidos(Model model, @ModelAttribute PedidosEntity pedidos, @RequestParam("mesaId") String mesa){       
    Integer idMesa = Integer.parseInt(mesa);
    mesasController.ocuparMesas(idMesa);
    
    pedidos.setMesasId(idMesa);    
    pedidos.setValorTotal(0);      
    pedidos.setStatus("ABERTO");
    
    pedidosService.criarPedido(pedidos);
    model.addAttribute("pedidos", pedidos);
    return "redirect:/cadastro-pedidos";  
    }
    
@GetMapping("/cadastro-pedidos") 
public String mostraMesas(Model model){    
    model.addAttribute("mesas", new MesasEntity()); 
    model.addAttribute("listaMesas", mesasService.listarTodasMesas()); 
    model.addAttribute("listaPedidos", pedidosService.listarTodosPedidos());
    return "cadastro-pedidos"; 
    }

@GetMapping("/alterar-pedido")
public String carregaPagina(Model model, @RequestParam("id") String id) {
    model.addAttribute("pedidoId", id);
    model.addAttribute("cardapio", new CardapioEntity()); 
    model.addAttribute("listaPrato", cardapioService.listarTodoCardapio()); 
    return "atualizar-pedido";      
    }

@GetMapping("/atualizar-pedidos")
public String atualizarPedido(Model model, @RequestParam("id") String id, 
                                           @RequestParam("idCardapio") String cardapioId, 
                                           @RequestParam("preco") double preco,
                                           @RequestParam("quantidade") int quantidade) {
    Integer idPedido = Integer.parseInt(id);     
    Integer idCardapio = Integer.parseInt(cardapioId);
    PedidosEntity p = pedidosService.buscarPorId(idPedido); 
    double valorTotal = preco*quantidade;
    pratosController.cadastrarPrato(idPedido, idCardapio, quantidade);
       
    p.setValorTotal(p.getValorTotal() + valorTotal);
    pedidosService.atualizarPedidos(idPedido, p);
    
    return "redirect:/alterar-pedido?id=" + id;       
    }

@GetMapping("/finalizar-pedido")
    public String finalizarPedido (@RequestParam("id") String id, @RequestParam("mesaId") String idMesa, @RequestParam("cpf") String cpf) {
      Integer idPedido = Integer.parseInt(id);
      Integer mesa = Integer.parseInt(idMesa);
      
      PedidosEntity p = pedidosService.buscarPorId(idPedido);
      
      p.setStatus("FINALIZADO"); 
      p.setCpfCliente(cpf);
      pedidosService.atualizarPedidos(idPedido, p);
      
      mesasController.desocuparMesas(mesa);
      
      return "redirect:/cadastro-pedidos";
    }
}
