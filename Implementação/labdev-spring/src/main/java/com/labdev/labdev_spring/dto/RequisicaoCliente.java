package com.labdev.labdev_spring.dto;
import com.labdev.labdev_spring.models.Cliente;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public class RequisicaoCliente {
  
  @Digits(integer = 20, fraction = 0, message = "{error.foo.fooId.size}")
  private long rg;

  @Digits(integer = 20, fraction = 0, message = "{error.foo.fooId.size}")
  private long cpf;

  @NotBlank
  private String nome;

  @NotBlank
  private String endereco;
  
  @NotBlank
  private String profissao;

  @NotBlank
  private String empregadora;

  @DecimalMin(value = "0.0", inclusive = false)
  private double rendimentos;
 
 @NotBlank
 private String email;

 @NotBlank
 private String senha;

 public long getRg() {
  return rg;
 }

 public void setRg(long rg) {
  this.rg = rg;
 }

 public long getCpf() {
  return cpf;
 }

 public void setCpf(long cpf) {
  this.cpf = cpf;
 }

 public String getNome() {
  return nome;
 }

 public void setNome(String nome) {
  this.nome = nome;
 }

 public String getEndereco() {
  return endereco;
 }

 public void setEndereco(String endereco) {
  this.endereco = endereco;
 }

 public String getProfissao() {
  return profissao;
 }

 public void setProfissao(String profissao) {
  this.profissao = profissao;
 }

 public String getEmpregadora() {
  return empregadora;
 }

 public void setEmpregadora(String empregadora) {
  this.empregadora = empregadora;
 }
 
 public double getRendimentos() {
  return rendimentos;
 }

 public void setRendimentos(double rendimentos) {
  this.rendimentos = rendimentos;
 }

 public Cliente toCliente(){
  Cliente cliente = new Cliente();
  cliente.setRg(this.rg);
  cliente.setCpf(this.cpf);
  cliente.setNome(this.nome);
  cliente.setEndereco(this.endereco);
  cliente.setProfissao(this.profissao);
  cliente.setEmpregadora(this.empregadora);
  cliente.setRendimentos(this.rendimentos);

  return cliente;
 }

 public void atualizarClienteExistente(Cliente cliente) {
  cliente.setRg(this.rg);
  cliente.setCpf(this.cpf);
  cliente.setNome(this.nome);
  cliente.setEndereco(this.endereco);
  cliente.setProfissao(this.profissao);
  cliente.setEmpregadora(this.empregadora);
  cliente.setRendimentos(this.rendimentos);
  // ⚠️ não altera email nem senha
}

 public void fromCliente(Cliente cliente){
  this.rg = cliente.getRg();
  this.cpf = cliente.getCpf();
  this.nome = cliente.getNome();
  this.endereco = cliente.getEndereco();
  this.profissao = cliente.getProfissao();
  this.empregadora = cliente.getEmpregadora();
  this.rendimentos = cliente.getRendimentos();
 }

 @Override
 public String toString() {
  return "RequisicaoCliente [rg=" + rg + ", cpf=" + cpf + ", nome=" + nome + ", endereco=" + endereco + ", profissao="
    + profissao + ", empregadora=" + empregadora + ", rendimentos=" + rendimentos + "]";
 }


}
