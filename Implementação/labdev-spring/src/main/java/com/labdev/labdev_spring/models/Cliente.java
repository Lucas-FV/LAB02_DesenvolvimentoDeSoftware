package com.labdev.labdev_spring.models;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;

@Entity
public class Cliente {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;

 @NonNull
 @Column(unique = true)
 private long cpf;

 @NonNull
 @Column(unique = true)
 private long rg;
 
 private String nome;
 private String endereco;
 private String profissao;
 private String empregadora;
 private double rendimentos;


 public Cliente (){

 }



 public Cliente(long cpf, long rg, String nome, String endereco, String profissao, String empregadora,
   double rendimentos) {
  this.cpf = cpf;
  this.rg = rg;
  this.nome = nome;
  this.endereco = endereco;
  this.profissao = profissao;
  this.empregadora = empregadora;
  this.rendimentos = rendimentos;
 }



 
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



 public long getId() {
  return id;
 }



 public void setId(long id) {
  this.id = id;
 }



 @Override
 public String toString() {
  return "Cliente [id=" + id + ", cpf=" + cpf + ", rg=" + rg + ", nome=" + nome + ", endereco=" + endereco
    + ", profissao=" + profissao + ", empregadora=" + empregadora + ", rendimentos=" + rendimentos + "]";
 }


 
}
