package com.labdev.labdev_spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.labdev.labdev_spring.models.Pedido;
import com.labdev.labdev_spring.repositories.PedidoRepository;

@Controller
@RequestMapping("/agente")
public class AgenteController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/pedidos")
    public String listarPedidosParaAgente(Model model) {
        List<Pedido> pedidos = pedidoRepository.findAll(); // agentes veem todos os pedidos
        model.addAttribute("pedidos", pedidos);
        return "painelAgente";
    }

    @PostMapping("/pedidos/{id}/aprovar")
    public String aprovarPedido(@PathVariable Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.setStatus("APROVADO");
            pedidoRepository.save(pedido);
        }
        return "redirect:/agente/pedidos";
    }

    @PostMapping("/pedidos/{id}/rejeitar")
    public String rejeitarPedido(@PathVariable Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.setStatus("REJEITADO");
            pedidoRepository.save(pedido);
        }
        return "redirect:/agente/pedidos";
    }
}
