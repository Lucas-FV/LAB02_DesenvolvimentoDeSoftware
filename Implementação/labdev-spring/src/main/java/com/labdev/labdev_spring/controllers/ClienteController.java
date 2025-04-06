package com.labdev.labdev_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.labdev.labdev_spring.dto.RequisicaoCliente;
import com.labdev.labdev_spring.models.Cliente;
import com.labdev.labdev_spring.repositories.ClienteRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClienteController {

 @Autowired
 private ClienteRepository clienteRepository;

 @GetMapping("/clientes")
 public ModelAndView index() {

  List<Cliente> clientes = this.clienteRepository.findAll();

  ModelAndView mv = new ModelAndView("clientes/index");
  mv.addObject("clientes", clientes);

  return mv;
 }

 @GetMapping("/clientes/cadastrar")
 public String cadastrar() {
  return "clientes/cadastrar";
 }

 @PostMapping("/clientes")
 public String create(@Valid RequisicaoCliente requisicao, BindingResult bindingResult) {

  if (bindingResult.hasErrors()) {
   System.out.println("Tem erros no formulário!!!");
   return "redirect:/clientes";
  } else {

   Cliente cliente = requisicao.toCliente();

   this.clienteRepository.save(cliente);

   return "redirect:/clientes";
  }
 }

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

 @GetMapping("/clientes/{id}/editar")
 public ModelAndView editar(@PathVariable Long id, RequisicaoCliente requisicao) {

  Optional<Cliente> optional = this.clienteRepository.findById(id);

  if (optional.isPresent()) {
   Cliente cliente = optional.get();
   requisicao.fromCliente(cliente); // popula a requisição
   ModelAndView mv = new ModelAndView("/clientes/editar");
   mv.addObject("requisicao", requisicao);
   mv.addObject("cliente", cliente);
   return mv;
  } else {
   return new ModelAndView("redirect:/clientes");
  }

 }

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

 @GetMapping("/clientes/{id}/deletar")
 public String deletar(@PathVariable Long id) {
  System.out.println("ID-------"+id);
  this.clienteRepository.deleteById(id);
  return "redirect:/clientes";
 }
}