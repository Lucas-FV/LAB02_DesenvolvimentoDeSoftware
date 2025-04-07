package com.labdev.labdev_spring.controllers;

import com.labdev.labdev_spring.models.Cliente;
import com.labdev.labdev_spring.repositories.ClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/perfil")
@Tag(name = "Perfil do Cliente", description = "Endpoints relacionados ao perfil do cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Operation(summary = "Exibir perfil do cliente logado")
    @GetMapping("/exibirPerfil")
    public String exibirPerfil(Model model, Principal principal) {
        String email = principal.getName();
        Cliente cliente = clienteRepository.findByEmail(email);

        if (cliente == null) {
            return "redirect:/login";
        }

        model.addAttribute("cliente", cliente);
        return "clientes/exibirPerfil";
    }

    @Operation(summary = "Exibir formulário de edição do perfil do cliente")
    @GetMapping("/editar/{id}")
    public String editarPerfil(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de cliente inválido: " + id));
        model.addAttribute("cliente", cliente);
        return "/clientes/editarPerfil";
    }

    @Operation(summary = "Salvar alterações no perfil do cliente")
    @PostMapping("/salvar")
    public String salvarPerfil(@ModelAttribute Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(clienteAtualizado.getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clienteAtualizado.setSenha(clienteExistente.getSenha());
        clienteRepository.save(clienteAtualizado);

        return "redirect:/perfil/exibirPerfil";
    }
}
