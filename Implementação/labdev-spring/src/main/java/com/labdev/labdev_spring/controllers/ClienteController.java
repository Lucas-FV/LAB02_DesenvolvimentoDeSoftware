package com.labdev.labdev_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.labdev.labdev_spring.dto.RequisicaoCliente;
import com.labdev.labdev_spring.models.Cliente;
import com.labdev.labdev_spring.repositories.ClienteRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@Tag(name = "Clientes", description = "Operações relacionadas aos clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Operation(summary = "Listar todos os clientes")
    @GetMapping("/clientes")
    public ModelAndView index() {
        List<Cliente> clientes = this.clienteRepository.findAll();
        ModelAndView mv = new ModelAndView("clientes/index");
        mv.addObject("clientes", clientes);
        return mv;
    }

    @Operation(summary = "Exibir formulário de cadastro de cliente")
    @GetMapping("/clientes/cadastrar")
    public String cadastrar() {
        return "clientes/cadastrar";
    }

    @Operation(summary = "Criar um novo cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Redireciona para a lista de clientes ou exibe erros de validação")
    })
    @PostMapping("/clientes")
    public String create(@Valid RequisicaoCliente requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/clientes";
        } else {
            Cliente cliente = requisicao.toCliente();
            this.clienteRepository.save(cliente);
            return "redirect:/clientes";
        }
    }

    @Operation(summary = "Obter detalhes de um cliente por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "302", description = "Cliente não encontrado, redireciona para lista")
    })
    @GetMapping("/clientes/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        Optional<Cliente> optional = this.clienteRepository.findById(id);
        if (optional.isPresent()) {
            Cliente cliente = optional.get();
            ModelAndView mv = new ModelAndView("/clientes/detalhes");
            mv.addObject("cliente", cliente);
            return mv;
        } else {
            return new ModelAndView("redirect:/clientes");
        }
    }

    @Operation(summary = "Exibir formulário de edição de um cliente")
    @GetMapping("/clientes/{id}/editar")
    public ModelAndView editar(@PathVariable Long id, RequisicaoCliente requisicao) {
        Optional<Cliente> optional = this.clienteRepository.findById(id);
        if (optional.isPresent()) {
            Cliente cliente = optional.get();
            requisicao.fromCliente(cliente);
            ModelAndView mv = new ModelAndView("/clientes/editar");
            mv.addObject("requisicao", requisicao);
            mv.addObject("cliente", cliente);
            return mv;
        } else {
            return new ModelAndView("redirect:/clientes");
        }
    }

    @Operation(summary = "Atualizar dados de um cliente")
    @PostMapping("/clientes/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoCliente requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        } else {
            Optional<Cliente> optional = this.clienteRepository.findById(id);
            if (optional.isPresent()) {
                Cliente cliente = requisicao.toCliente(optional.get());
                this.clienteRepository.save(cliente);
                return new ModelAndView("redirect:/clientes/" + cliente.getId());
            } else {
                return new ModelAndView("redirect:/clientes");
            }
        }
    }

    @Operation(summary = "Deletar um cliente por ID")
    @GetMapping("/clientes/{id}/deletar")
    public String deletar(@PathVariable Long id) {
        this.clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }

    @Operation(summary = "Obter perfil do cliente autenticado")
    @GetMapping("/perfil")
    public ModelAndView perfil(@AuthenticationPrincipal UserDetails userDetails) {
        Cliente cliente = clienteRepository.findByEmail(userDetails.getUsername());
        if (cliente == null) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mv = new ModelAndView("perfil");
        mv.addObject("cliente", cliente);
        return mv;
    }

    @Operation(summary = "Editar perfil do cliente autenticado")
    @PostMapping("/perfil/editar")
    public String editarPerfil(@ModelAttribute Cliente clienteAtualizado) {
        clienteRepository.save(clienteAtualizado);
        return "redirect:/clientes/perfil";
    }

    @Operation(summary = "Excluir perfil do cliente autenticado")
    @PostMapping("/perfil/excluir")
    public String excluirPerfil(@RequestParam Long id, HttpServletRequest request) {
        clienteRepository.deleteById(id);
        request.getSession().invalidate();
        return "redirect:/";
    }
}
