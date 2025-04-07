package com.labdev.labdev_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.labdev.labdev_spring.models.Cliente;
import com.labdev.labdev_spring.repositories.ClienteRepository;

import jakarta.validation.Valid;

@Controller
public class RegistroController {

 @Autowired
 private ClienteRepository clienteRepository;

 @Autowired
 private PasswordEncoder passwordEncoder;

 @GetMapping("/registro")
 public String mostrarFormulario(Model model) {
  model.addAttribute("cliente", new Cliente());
  return "clientes/registro"; // isso deve apontar para templates/clientes/registro.html
 }

 @PostMapping("/registro")
 public String processarRegistro(@Valid Cliente cliente, BindingResult result) {
  if (result.hasErrors()) {
   return "/clientes/registro";
  }

  // Criptografa a senha antes de salvar
  cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
  clienteRepository.save(cliente);
  return "redirect:/login";
 }
}