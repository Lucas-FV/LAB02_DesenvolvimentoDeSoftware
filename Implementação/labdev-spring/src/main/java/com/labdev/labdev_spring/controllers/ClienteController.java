package com.labdev.labdev_spring.controllers;

import com.labdev.labdev_spring.models.Cliente;
import com.labdev.labdev_spring.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ClienteController {

 @Autowired
 private ClienteRepository clienteRepository;

 // Exibir Perfil

 @GetMapping("/perfil/exibirPerfil")
 public String exibirPerfil(Model model, Principal principal) {
  String email = principal.getName(); // ou getUsername() se for o caso
  Cliente cliente = clienteRepository.findByEmail(email);

  if (cliente == null) {
   return "redirect:/login"; // segurança extra: se não achar, redireciona
  }

  model.addAttribute("cliente", cliente);
  return "clientes/exibirPerfil"; // templates/perfil/exibirPerfil.html
 }

 // Exibir Editar Perfil

 @GetMapping("/perfil/editar/{id}")
 public String editarPerfil(@PathVariable Long id, Model model) {
  Cliente cliente = clienteRepository.findById(id)
    .orElseThrow(() -> new IllegalArgumentException("ID de cliente inválido: " + id));
  model.addAttribute("cliente", cliente);
  return "/clientes/editarPerfil";
 }

 // Alterar Perfil

 @PostMapping("/perfil/salvar")
 public String salvarPerfil(@ModelAttribute Cliente clienteAtualizado) {
  // Buscar o cliente original no banco
  Cliente clienteExistente = clienteRepository.findById(clienteAtualizado.getId())
    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

  // Preservar a senha original
  clienteAtualizado.setSenha(clienteExistente.getSenha());

  // Salvar o cliente atualizado (com senha mantida)
  clienteRepository.save(clienteAtualizado);

  return "redirect:/perfil/exibirPerfil";
 }
}
