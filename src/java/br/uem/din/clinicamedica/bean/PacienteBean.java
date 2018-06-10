/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.bean;

/**
 *
 * @author miche
 */

import br.uem.din.clinicamedica.controller.PacienteController;
import br.uem.din.clinicamedica.model.Paciente;
import br.uem.din.clinicamedica.model.Usuario;
import br.uem.din.clinicamedica.model.utils.Endereco;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "PacienteBean")
@RequestScoped
public class PacienteBean {
    private String nome;
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String consultaPacientes(ServletRequest request){
        HttpSession sess = ((HttpServletRequest) request).getSession(true);
        Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
        return u.getTipo().menuPaciente();
    }
    
    public List<Paciente> listarPacientes(){
        return PacienteController.getInstance().listarPacientes();
    }
}
