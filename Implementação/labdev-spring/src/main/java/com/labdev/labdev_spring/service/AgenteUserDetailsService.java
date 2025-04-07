package com.labdev.labdev_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.labdev.labdev_spring.models.Agente;
import com.labdev.labdev_spring.repositories.AgenteRepository;

@Service
public class AgenteUserDetailsService implements UserDetailsService {

 @Autowired
 private AgenteRepository agenteRepository;

 @Override
 public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
  Agente agente = agenteRepository.findByEmail(email)
    .orElseThrow(() -> new UsernameNotFoundException("Agente não encontrado"));

  return User.builder()
    .username(agente.getEmail())
    .password(agente.getSenha())
    .roles("AGENTE") // Não precisa escrever "ROLE_" aqui
    .build();
 }
}