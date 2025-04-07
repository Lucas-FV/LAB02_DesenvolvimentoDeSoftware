package com.labdev.labdev_spring.controllers;

import java.util.List;
import java.util.Optional;

import com.labdev.labdev_spring.dto.RequisicaoPedido;
import com.labdev.labdev_spring.models.Cliente;
import com.labdev.labdev_spring.models.Pedido;
import com.labdev.labdev_spring.repositories.ClienteRepository;
import com.labdev.labdev_spring.repositories.PedidoRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PedidoController {

 @Autowired
 private PedidoRepository pedidoRepository;

 @Autowired
 private ClienteRepository clienteRepository;

 @GetMapping("/pedidos")
 public ModelAndView index() {
  List<Pedido> pedidos = pedidoRepository.findAll();
  ModelAndView mv = new ModelAndView("pedidos/listarPedido");
  mv.addObject("pedidos", pedidos);
  return mv;
 }

 @GetMapping("/pedidos/novo")
 public ModelAndView cadastrar(RequisicaoPedido requisicao) {
  ModelAndView mv = new ModelAndView("pedidos/criarPedido");
  mv.addObject("clientes", clienteRepository.findAll());
  return mv;
 }

 @PostMapping("/pedidos")
 public ModelAndView create(@Valid RequisicaoPedido requisicao, BindingResult result) {
  if (result.hasErrors()) {
   ModelAndView mv = new ModelAndView("pedidos/criarPedido");
   mv.addObject("clientes", clienteRepository.findAll());
   return mv;
  }

  Cliente cliente = clienteRepository.findById(requisicao.clienteId).orElse(null);
  Pedido pedido = requisicao.toPedido(cliente);
  pedido.setCliente(cliente);
  pedidoRepository.save(pedido);

  return new ModelAndView("redirect:/pedidos");
 }

 @GetMapping("/pedidos/{id:[0-9]+}")
public ModelAndView detalhes(@PathVariable Long id) {
    Optional<Pedido> optional = pedidoRepository.findById(id);
    if (optional.isPresent()) {
        ModelAndView mv = new ModelAndView("pedidos/detalhesPedido"); // 👈 aqui
        mv.addObject("pedido", optional.get());
        return mv;
    } else {
        return new ModelAndView("redirect:/pedidos");
    }
}

@GetMapping("/pedidos/{id}/editar")
public ModelAndView editar(@PathVariable Long id) {
    Optional<Pedido> optional = pedidoRepository.findById(id);
    if (optional.isPresent()) {
        Pedido pedido = optional.get();
        RequisicaoPedido requisicao = new RequisicaoPedido();
        requisicao.fromPedido(pedido);  // <-- Aqui está o ajuste

        ModelAndView mv = new ModelAndView("pedidos/editarPedido");
        mv.addObject("pedidoId", pedido.getId());
        mv.addObject("requisicaoPedido", requisicao);
        mv.addObject("clientes", clienteRepository.findAll());
        return mv;
    } else {
        return new ModelAndView("redirect:/pedidos");
    }
}

@PostMapping("/pedidos/{id}")
public ModelAndView atualizar(@PathVariable Long id, @Valid RequisicaoPedido requisicao, BindingResult result) {
    Optional<Pedido> optional = pedidoRepository.findById(id);

    if (optional.isEmpty()) {
        return new ModelAndView("redirect:/pedidos");
    }

    Pedido pedidoExistente = optional.get();

    if (result.hasErrors()) {
        // Preenche o formulário com os dados já preenchidos e mostra os erros
        ModelAndView mv = new ModelAndView("pedidos/editarPedido");
        mv.addObject("requisicaoPedido", requisicao); // mantém os dados do form
        mv.addObject("clientes", clienteRepository.findAll());
        mv.addObject("pedidoId", id); // caso precise no formulário
        return mv;
    }

    Optional<Cliente> clienteOptional = clienteRepository.findById(requisicao.getClienteId());
    if (clienteOptional.isEmpty()) {
        return new ModelAndView("redirect:/pedidos");
    }

    Cliente cliente = clienteOptional.get();

    // Atualiza os dados no objeto existente
    requisicao.toPedido(pedidoExistente, cliente);

    pedidoRepository.save(pedidoExistente);

    return new ModelAndView("redirect:/pedidos/" + pedidoExistente.getId()); // redireciona para detalhes
}
 @GetMapping("/pedidos/{id}/deletar")
 public String deletar(@PathVariable Long id) {
  pedidoRepository.deleteById(id);
  return "redirect:/pedidos";
 }
}