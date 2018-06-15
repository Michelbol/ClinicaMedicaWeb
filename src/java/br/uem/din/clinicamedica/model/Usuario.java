/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.model;
import br.uem.din.clinicamedica.model.utils.Cidade;
import br.uem.din.clinicamedica.model.utils.Endereco;
import br.uem.din.clinicamedica.model.utils.Telefone;
import br.uem.din.clinicamedica.model.utils.TipoUsuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author miche
 */
public class Usuario extends Pessoa{
    private TipoUsuario tipo;
    private String username;
    private String password;

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public Usuario(int id, TipoUsuario tipo, String nome, String sobrenome, String cpf, String rg, Date dataNascimento, Endereco endereco, Telefone residencial, Telefone celular, String email) {
        super(id, nome, sobrenome, cpf, rg, dataNascimento, endereco, residencial, celular, email);
        this.tipo = tipo;
    }

    public Usuario() {
    }
    
    public static List<Usuario> povoarUsuarios(){
        List<Usuario> usuarios = new ArrayList();
        Usuario u = new Usuario();
        u.setId(1);
        u.setNome("José");
        u.setSobrenome("da Silva");
        u.setResidencial(new Telefone("55", "44", "3028", "2998"));
        u.setRg("7.778.003-53");
        u.setTipo(TipoUsuario.Medico);
        u.setCelular(new Telefone("55", "44", "99824", "6655"));
        u.setCpf("123.232.345-12");
        u.setDataNascimento(new Date("11/10/1995"));
        u.setEmail("michel_ra83558@gmail.com");
        u.setEndereco(new Endereco(new Cidade("Maringá", "PR", "Brasil"), "Travessa Ruboso", "8956", "Zona 03"));
        usuarios.add(u);
        u = new Usuario();
        u.setId(2);
        u.setNome("Maria");
        u.setSobrenome("de Oliveira");
        u.setResidencial(new Telefone("55", "44", "3228", "9963"));
        u.setRg("9.996.220-78");
        u.setTipo(TipoUsuario.Secretaria);
        u.setCelular(new Telefone("55", "44", "99654", "2233"));
        u.setCpf("321.356.785-51");
        u.setDataNascimento(new Date("21/01/1997"));
        u.setEmail("joao_ra8899@gmail.com");
        u.setEndereco(new Endereco(new Cidade("Londrina", "PR", "Brasil"), "Av Brasil", "3256", "Zona 07"));
        u.setUsername("secretaria");
        u.setPassword("123456");
        usuarios.add(u);
        u = new Usuario();
        u.setId(3);
        u.setNome("João");
        u.setSobrenome("da Paraiba");
        u.setResidencial(new Telefone("55", "44", "30223", "5200"));
        u.setRg("7.778.879-53");
        u.setTipo(TipoUsuario.Medico);
        u.setCelular(new Telefone("55", "44", "99966", "6655"));
        u.setCpf("123.328.345-12");
        u.setDataNascimento(new Date("14/07/1980"));
        u.setEmail("joão_ra8899@gmail.com");
        u.setEndereco(new Endereco(new Cidade("Curitiba", "PR", "Brasil"), "Av paraná", "464", "Zona 03"));
        u.setUsername("medico");
        u.setPassword("123456");
        usuarios.add(u);
        return usuarios;
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + getId() + ", tipo=" + tipo + ", username=" + username + ", password=" + password + ", nome =" + this.getNome() +'}';
    }
}
