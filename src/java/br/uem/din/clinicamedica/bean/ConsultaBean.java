/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.bean;

import br.uem.din.clinicamedica.controller.ConsultaController;
import br.uem.din.clinicamedica.controller.PacienteController;
import br.uem.din.clinicamedica.controller.UsuarioController;
import br.uem.din.clinicamedica.model.Consulta;
import br.uem.din.clinicamedica.model.Paciente;
import br.uem.din.clinicamedica.model.Usuario;
import br.uem.din.clinicamedica.model.utils.TipoConsulta;
import br.uem.din.clinicamedica.model.utils.Utils;
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
 * @author miche
 */
@ManagedBean(name = "ConsultaBean")
@RequestScoped
public class ConsultaBean {
    private int id;
    private String data;
    private String hora;
    private int medico;
    private int paciente;
    private TipoConsulta tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getMedico() {
        return medico;
    }

    public void setMedico(int medico) {
        this.medico = medico;
    }

    public int getPaciente() {
        return paciente;
    }

    public void setPaciente(int paciente) {
        this.paciente = paciente;
    }

    public TipoConsulta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConsulta tipo) {
        this.tipo = tipo;
    }
    
    public void preencherBean(Consulta c) {
        this.id         = c.getId();
        if(c.getDataHora() != null ){
            String datahora = c.getDataHora();
            this.data   = datahora.split(" ")[0];
            this.hora   = datahora.split(" ")[1];
        }
        this.medico     = c.getMedico().getId();
        this.paciente   = c.getPaciente().getId();
        this.tipo       = c.getTipo();
    }
    
    public List<Consulta> listarConsultas(){
        return ConsultaController.getInstance().listarConsultas();
    }
    
    public List<Usuario> listarMedicos(){
        return UsuarioController.getInstance().listarMedicos();
    }
    public List<Paciente> listarPacientes(){
        return PacienteController.getInstance().listarPacientes();
    }
    
    public String consultasMedicas(ServletRequest request){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            if(u != null){
                return "SECRETARIA_consultaMedica.xhtml";
            }else{
                FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Usuário não está logado"));
                return "index.xhtml";
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Erro: "+e.getMessage()));
            return "index.xhtml";
        }    
    }
    
    public TipoConsulta[] tipos(){
        return TipoConsulta.values();
    }
    
    public String incluir(ServletRequest request){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            if(u != null){
                return "SECRETARIA_incluirConsulta.xhtml";
            }else{
                FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Usuário não está logado"));
                return "index.xhtml";
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Erro: "+e.getMessage()));
            return "index.xhtml";
        }   
    }
    
    public String salvar(ServletRequest request){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            if(u != null){
                ConsultaController.getInstance().salvarConsulta(new Consulta(Utils.stringToDateTime(data+" "+hora), UsuarioController.getInstance().findUsuario(medico), PacienteController.getInstance().findPaciente(paciente), tipo));
                return "SECRETARIA_consultaMedica.xhtml";
            }else{
                FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Usuário não está logado"));
                return "index.xhtml";
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Erro: "+e.getMessage()));
            return "index.xhtml";
        }   
    }
    
    public String excluir(ServletRequest request, int id){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            if(u != null){
                ConsultaController.getInstance().excluirConsulta(id);
                return "SECRETARIA_consultaMedica.xhtml";
            }else{
                FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Usuário não está logado"));
                return "index.xhtml";
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage(e.getMessage()));
            return "index.xhtml";
        }         
    }
    
    public String editar(ServletRequest request, int id){
            try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            if(u != null){
                Consulta c = ConsultaController.getInstance().findConsulta(id);
                preencherBean(c);
                return "SECRETARIA_editarConsulta.xhtml";
            }else{
                FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Usuário não está logado"));
                return "index.xhtml";
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage(e.getMessage()));
            return "index.xhtml";
        }  
    }
    
    public String atualizar(ServletRequest request){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            if(u != null){
                ConsultaController.getInstance().atualizarConsulta(new Consulta(id,Utils.stringToDateTime(data+" "+hora), UsuarioController.getInstance().findUsuario(medico), PacienteController.getInstance().findPaciente(paciente), tipo));
                return "SECRETARIA_consultaMedica.xhtml";
            }else{
                FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Usuário não está logado"));
                return "index.xhtml";
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage(e.getMessage()));
            return "index.xhtml";
        }
    }
    public String telaRelatorio(ServletRequest request){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            if(u != null){
                return "SECRETARIA_relatorio.xhtml";
            }else{
                FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Usuário não está logado"));
                return "index.xhtml";
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage(e.getMessage()));
            return "index.xhtml";
        }
    }
}
