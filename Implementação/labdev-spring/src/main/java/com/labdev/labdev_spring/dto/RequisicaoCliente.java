package com.labdev.labdev_spring.dto;

import com.labdev.labdev_spring.models.Cliente;

public class RequisicaoCliente {

 private long rg;
 private long cpf;
 private String nome;
 private String endereco;
 private String profissao;
 private String empregadora;
 private double rendimentos;

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

 @Override
 public String toString() {
  return "RequisicaoCliente [rg=" + rg + ", cpf=" + cpf + ", nome=" + nome + ", endereco=" + endereco + ", profissao="
    + profissao + ", empregadora=" + empregadora + ", rendimentos=" + rendimentos + "]";
 }


}
