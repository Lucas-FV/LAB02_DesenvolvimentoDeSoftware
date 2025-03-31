package com.labdev.labdev_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.labdev.labdev_spring.models.Cliente;
import com.labdev.labdev_spring.repositories.ClienteRepository;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;



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
 public String cadastrar(){
  return "clientes/cadastrar";
 }
 
 @PostMapping("/clientes")
 public String create(Cliente cliente){
  return "redirect:/clientes";
 }
}