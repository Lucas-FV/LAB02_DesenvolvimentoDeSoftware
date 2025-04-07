package com.labdev.labdev_spring.models;

import jakarta.persistence.*;

@Entity
public class Pedido {

    @ManyToOne
    private Cliente cliente;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean parecer;

    private String agente;

    private String status;

    private String automovel = "Automóvel padrão"; // valor fixo

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isParecer() {
        return parecer;
    }

    public void setParecer(boolean parecer) {
        this.parecer = parecer;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public String getAutomovel() {
        return automovel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}