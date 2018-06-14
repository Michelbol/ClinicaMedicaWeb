/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.bean;

import br.uem.din.clinicamedica.controller.ProntuarioController;
import br.uem.din.clinicamedica.model.Paciente;
import br.uem.din.clinicamedica.model.Prontuario;
import br.uem.din.clinicamedica.model.Usuario;
import br.uem.din.clinicamedica.model.utils.Utils;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author joaov
 */

@ManagedBean(name = "ProntuarioBean")
@RequestScoped
public class ProntuarioBean {
    private long id;
    private Paciente paciente;
    private Usuario medico;
    private String sintomas;
    private String diagnostico;
    private String prescricao;
    private String data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Paciente getIdPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public Usuario getMedico() {
        return medico;
    }

    public void setMedico(Usuario medico) {
        this.medico = medico;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
 
    public String consultaProntuarios(ServletRequest request){
        HttpSession sess = ((HttpServletRequest) request).getSession(true);
        Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
        return u.getTipo().menuProntuario();
    }
    
    public List<Prontuario> listarProntuarios(){
        return ProntuarioController.getInstance().listarProntuarios();
    }
    
    public String incluir(ServletRequest request){
        HttpSession sess = ((HttpServletRequest) request).getSession(true);
        Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
        return u.getTipo().incluirProntuario();        
    }
    
    public String editar(ServletRequest request, int id){
        HttpSession sess = ((HttpServletRequest) request).getSession(true);
        Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
        return u.getTipo().editarProntuario(id);
    }
    
    public String excluir(ServletRequest request, int id){
        HttpSession sess = ((HttpServletRequest) request).getSession(true);
        Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
        return u.getTipo().excluir(id);        
    }
    
    public String consultaRelatorios(ServletRequest request){
        HttpSession sess = ((HttpServletRequest) request).getSession(true);
        Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
        return u.getTipo().menuRelatorios();
    } 
    
    public void salvar(HttpSession request){
        HttpSession sess = ((HttpServletRequest) request).getSession(true);
        Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
        
        ProntuarioController.getInstance().salvarProntuario(new Prontuario(paciente,u,sintomas,diagnostico,prescricao,Utils.stringToDate(data)));
    }
}
