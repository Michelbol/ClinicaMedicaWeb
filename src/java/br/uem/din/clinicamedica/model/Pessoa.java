/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.model;

import br.uem.din.clinicamedica.model.utils.Endereco;
import br.uem.din.clinicamedica.model.utils.Telefone;
import java.util.Date;

/**
 *
 * @author miche
 */
public class Pessoa {
    private int id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String rg;
    private Date dataNascimento;
    private Endereco endereco;
    private Telefone residencial;
    private Telefone celular;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getResidencial() {
        return residencial;
    }

    public void setResidencial(Telefone residencial) {
        this.residencial = residencial;
    }

    public Telefone getCelular() {
        return celular;
    }

    public void setCelular(Telefone celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pessoa{" +"id=" + id + "nome=" + nome + ", sobrenome=" + sobrenome + ", cpf=" + cpf + ", rg=" + rg + ", dataNascimento=" + dataNascimento + ", endereco=" + endereco + ", residencial=" + residencial + ", celular=" + celular + ", email=" + email + '}';
    }

    public Pessoa(int id, String nome, String sobrenome, String cpf, String rg, Date dataNascimento, Endereco endereco, Telefone residencial, Telefone celular, String email) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.residencial = residencial;
        this.celular = celular;
        this.email = email;
    }

    public Pessoa() {
    }  
    
    public boolean emailPreenchido(){
        return (this.email.length() > 0);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
