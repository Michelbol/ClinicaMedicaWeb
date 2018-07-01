/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.bean;

import br.uem.din.clinicamedica.controller.PacienteController;
import br.uem.din.clinicamedica.controller.ProntuarioController;
import br.uem.din.clinicamedica.model.Paciente;
import br.uem.din.clinicamedica.model.Prontuario;
import br.uem.din.clinicamedica.model.Usuario;
import br.uem.din.clinicamedica.model.utils.Utils;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
    private int id;
    private int idPaciente;
    private Usuario medico;
    private String sintomas;
    private String diagnostico;
    private String prescricao;
    private String data;
    private String hora;
    
    public void preencherBean(Prontuario prontuario) {
        this.id = prontuario.getId();
        this.idPaciente = prontuario.getPaciente().getId();
        this.medico = prontuario.getMedico();
        this.sintomas = prontuario.getSintomas();
        this.diagnostico = prontuario.getDiagnostico();
        this.prescricao = prontuario.getPrescricao();
        
        if(prontuario.getDataHora() != null ){
            String datahora = prontuario.getDataHora();
            this.data   = datahora.split(" ")[0];
            this.hora   = datahora.split(" ")[1];
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaciente() {
        return idPaciente;
    }

    public void setPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
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

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    
 
    public String consultaProntuarios(ServletRequest request){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            if(u != null){
                return u.getTipo().menuProntuario();
            }else{
                FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Usuário não está logado!"));
                return "index.xhtml";
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage(e.getMessage()));
            return "index.xhtml";
        }
    }
    
    public List<Prontuario> listarProntuarios(){
        return ProntuarioController.getInstance().listarProntuarios();
    }
    
    public String incluir(ServletRequest request){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            return u.getTipo().incluirProntuario(); 
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage(e.getMessage()));
            return "/index";
        }       
    }
    
    public String editar(ServletRequest request, Prontuario prontuarioTela){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            Prontuario pBean = ProntuarioController.getInstance().findProntuario(prontuarioTela.getId());
            preencherBean(pBean);
            return u.getTipo().editarProntuario(id);
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage(e.getMessage()));
            return "/index";
        } 
    }
    
    public String excluir(ServletRequest request, Prontuario p){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            ProntuarioController.getInstance().excluirProntuario(p.getId());
            return "MEDICO_consultaProntuario.xhtml";
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage(e.getMessage()));
            return "/index";
        }         
    }
    
    public String consultaRelatorios(ServletRequest request){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            if(u != null){
                return u.getTipo().menuRelatorios();
            }else{
                FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Usuário não está logado!"));
                return "index.xhtml";
            }
        }catch(Exception e){
            return "index.xhtml";
        }
    } 
    
    public String salvar(ServletRequest request){
        HttpSession sess = ((HttpServletRequest) request).getSession(true);
        Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
        Paciente pacienteTela = PacienteController.getInstance().findPaciente(idPaciente);
        ProntuarioController.getInstance().salvarProntuario(new Prontuario(pacienteTela,u,sintomas,diagnostico,prescricao,Utils.stringToDateTime(data+" "+hora)));
        return "MEDICO_consultaProntuario.xhtml";
    }
    
    public String atualizar(){
        try{
            Paciente pacienteTela = PacienteController.getInstance().findPaciente(idPaciente);
            ProntuarioController.getInstance().atualizarPaciente(new Prontuario(id,pacienteTela,sintomas,diagnostico,prescricao,Utils.stringToDateTime(data+" "+hora)));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage(e.getMessage()));
            return "index.xhtml";
        }
        return "MEDICO_consultaProntuario.xhtml";
    }
}
