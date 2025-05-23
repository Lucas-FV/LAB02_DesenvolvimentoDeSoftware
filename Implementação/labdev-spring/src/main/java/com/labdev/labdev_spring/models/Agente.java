package com.labdev.labdev_spring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Agente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    private String role = "ROLE_AGENTE";


    

    public Agente() {
    }

    public Long getId() {
     return id;
    }

    public void setId(Long id) {
     this.id = id;
    }

    public String getNome() {
     return nome;
    }

    public void setNome(String nome) {
     this.nome = nome;
    }

    public String getEmail() {
     return email;
    }

    public void setEmail(String email) {
     this.email = email;
    }

    public String getSenha() {
     return senha;
    }

    public void setSenha(String senha) {
     this.senha = senha;
    }

    public String getRole() {
     return role;
    }

    public void setRole(String role) {
     this.role = role;
    }

    
}