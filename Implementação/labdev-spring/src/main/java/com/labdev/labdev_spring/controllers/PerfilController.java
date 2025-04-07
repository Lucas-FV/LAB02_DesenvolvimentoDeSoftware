package com.labdev.labdev_spring.controllers;

import com.labdev.labdev_spring.models.Cliente;
import com.labdev.labdev_spring.repositories.ClienteRepository;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

private final ClienteRepository clienteRepository;


 public PerfilController(ClienteRepository clienteRepository) {
  this.clienteRepository = clienteRepository;
 }

 @GetMapping("/exibirPerfil")
 public String exibirPerfil(Model model, Principal principal) {
  String email = principal.getName(); // pega o e-mail do usuário logado
  Cliente cliente = clienteRepository.findByEmail(email); // busca o cliente no banco
  model.addAttribute("cliente", cliente);
  return "/clientes/perfil"; // retorna para o HTML perfil.html
 }

 @GetMapping("/editar")
 public String mostrarFormularioEdicao(Model model, @AuthenticationPrincipal UserDetails userDetails) {
  Cliente cliente = clienteRepository.findByEmail(userDetails.getUsername());
  model.addAttribute("cliente", cliente);
  return "/clientes/editar-perfil";
 }
}