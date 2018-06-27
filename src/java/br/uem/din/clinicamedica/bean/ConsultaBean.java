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
    private String dataHora;
    private int medico;
    private int paciente;
    private TipoConsulta tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataHora() {
        return dataHora;
        }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
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
            this.dataHora   = c.getDataHora();
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
                return "index.xhtml"; 
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage(e.getMessage()));
            return "index.xhtml";
        }    
    }
    
    public TipoConsulta[] tipos(){
        return TipoConsulta.values();
    }
    
    public String incluir(){
        return "SECRETARIA_incluirConsulta.xhtml";
    }
    
    public String salvar(){
        ConsultaController.getInstance().salvarConsulta(new Consulta(Utils.stringToDateTime(dataHora), UsuarioController.getInstance().findUsuario(medico), PacienteController.getInstance().findPaciente(paciente), tipo));
        return "SECRETARIA_consultaMedica.xhtml";
    }
    
    public String excluir(int id){
        try{
            ConsultaController.getInstance().excluirConsulta(id);
            return "SECRETARIA_consultaMedica.xhtml";
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage(e.getMessage()));
            return "index.xhtml";
        }         
    }
    
    public String editar(int id){
            Consulta c = ConsultaController.getInstance().findConsulta(id);
            preencherBean(c);
            return "SECRETARIA_editarConsulta.xhtml";
    }
    
    public String atualizar(){
        try{
            ConsultaController.getInstance().atualizarConsulta(new Consulta(id,Utils.stringToDateTime(dataHora), UsuarioController.getInstance().findUsuario(medico), PacienteController.getInstance().findPaciente(paciente), tipo));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage(e.getMessage()));
            return "index.xhtml";
        }
        return "SECRETARIA_consultaMedica.xhtml";
    }
}
