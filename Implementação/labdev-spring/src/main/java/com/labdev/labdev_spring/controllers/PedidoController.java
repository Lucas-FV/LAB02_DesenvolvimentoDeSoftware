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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@Tag(name = "Pedidos", description = "Operações relacionadas aos pedidos de clientes")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Operation(summary = "Listar todos os pedidos")
    @GetMapping("/pedidos")
    public ModelAndView index() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        ModelAndView mv = new ModelAndView("pedidos/listarPedido");
        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @Operation(summary = "Exibir formulário para criação de um novo pedido")
    @GetMapping("/pedidos/novo")
    public ModelAndView cadastrar(RequisicaoPedido requisicao) {
        ModelAndView mv = new ModelAndView("pedidos/criarPedido");
        mv.addObject("clientes", clienteRepository.findAll());
        return mv;
    }

    @Operation(summary = "Criar um novo pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
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

    @Operation(summary = "Obter detalhes de um pedido por ID")
    @GetMapping("/pedidos/{id:[0-9]+}")
    public ModelAndView detalhes(@PathVariable Long id) {
        Optional<Pedido> optional = pedidoRepository.findById(id);
        if (optional.isPresent()) {
            ModelAndView mv = new ModelAndView("pedidos/detalhesPedido");
            mv.addObject("pedido", optional.get());
            return mv;
        } else {
            return new ModelAndView("redirect:/pedidos");
        }
    }

    @Operation(summary = "Exibir formulário para edição de um pedido")
    @GetMapping("/pedidos/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        Optional<Pedido> optional = pedidoRepository.findById(id);
        if (optional.isPresent()) {
            Pedido pedido = optional.get();
            RequisicaoPedido requisicao = new RequisicaoPedido();
            requisicao.fromPedido(pedido);

            ModelAndView mv = new ModelAndView("pedidos/editarPedido");
            mv.addObject("pedidoId", pedido.getId());
            mv.addObject("requisicaoPedido", requisicao);
            mv.addObject("clientes", clienteRepository.findAll());
            return mv;
        } else {
            return new ModelAndView("redirect:/pedidos");
        }
    }

    @Operation(summary = "Atualizar um pedido existente")
    @PostMapping("/pedidos/{id}")
    public ModelAndView atualizar(@PathVariable Long id, @Valid RequisicaoPedido requisicao, BindingResult result) {
        Optional<Pedido> optional = pedidoRepository.findById(id);

        if (optional.isEmpty()) {
            return new ModelAndView("redirect:/pedidos");
        }

        Pedido pedidoExistente = optional.get();

        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("pedidos/editarPedido");
            mv.addObject("requisicaoPedido", requisicao);
            mv.addObject("clientes", clienteRepository.findAll());
            mv.addObject("pedidoId", id);
            return mv;
        }

        Optional<Cliente> clienteOptional = clienteRepository.findById(requisicao.getClienteId());
        if (clienteOptional.isEmpty()) {
            return new ModelAndView("redirect:/pedidos");
        }

        Cliente cliente = clienteOptional.get();
        requisicao.toPedido(pedidoExistente, cliente);

        pedidoRepository.save(pedidoExistente);

        return new ModelAndView("redirect:/pedidos/" + pedidoExistente.getId());
    }

    @Operation(summary = "Excluir um pedido por ID")
    @GetMapping("/pedidos/{id}/deletar")
    public String deletar(@PathVariable Long id) {
        pedidoRepository.deleteById(id);
        return "redirect:/pedidos";
    }
}
