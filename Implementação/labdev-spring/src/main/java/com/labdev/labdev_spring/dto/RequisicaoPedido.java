package com.labdev.labdev_spring.dto;

import com.labdev.labdev_spring.models.Cliente;
import com.labdev.labdev_spring.models.Pedido;

public class RequisicaoPedido {

    public Long id;            // ID do pedido
    public boolean parecer;
    public Long clienteId;
    public String agente;
    public String status;

    public Pedido toPedido(Cliente cliente) {
        Pedido pedido = new Pedido();
        pedido.setParecer(parecer);
        pedido.setCliente(cliente);
        pedido.setAgente(agente);
        pedido.setStatus(status);
        return pedido;
    }

    public Pedido toPedido(Pedido pedido, Cliente cliente) {
        pedido.setParecer(parecer);
        pedido.setCliente(cliente);
        pedido.setAgente(agente);
        pedido.setStatus(status);
        return pedido;
    }

    public void fromPedido(Pedido pedido) {
        this.id = pedido.getId(); // Preenchendo o ID
        this.parecer = pedido.isParecer();
        this.clienteId = pedido.getCliente().getId();
        this.agente = pedido.getAgente();
        this.status = pedido.getStatus();
    }

    // Getters e setters

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

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}