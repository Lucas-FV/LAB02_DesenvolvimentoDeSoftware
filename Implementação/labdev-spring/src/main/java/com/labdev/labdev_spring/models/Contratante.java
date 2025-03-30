package com.labdev.labdev_spring.models;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;

@Entity
public class Contratante {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;

 @NonNull
 @Column(unique = true)
 private long cpf;

 @NonNull
 @Column(unique = true)
 private long rg;
 
 private String endereco;
 private String profissao;
 private String empregadora;
 private double rendimentos;


 public Contratante (){

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

 
}
