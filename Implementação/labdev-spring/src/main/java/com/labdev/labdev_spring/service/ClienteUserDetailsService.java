package com.labdev.labdev_spring.service;

import com.labdev.labdev_spring.models.Cliente;
import com.labdev.labdev_spring.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class ClienteUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new UsernameNotFoundException("Cliente não encontrado com o email: " + email);
        }

        return User.builder()
                .username(cliente.getEmail())
                .password(cliente.getSenha())
                .roles("USER") // ou "CLIENTE", se quiser diferenciar
                .build();
    }
}
