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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    private String filtroDataInicial;
    private String filtroDataFinal;
    private String filtroHoraInicial;
    private String filtroHoraFinal;
    private int filtroMedico;
    private int filtroPaciente;
    private TipoConsulta filtroTipo;
    private List<Consulta> lista_filtrada;

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

    public String getFiltroDataInicial() {
        return filtroDataInicial;
    }

    public void setFiltroDataInicial(String filtroDataInicial) {
        this.filtroDataInicial = filtroDataInicial;
    }

    public String getFiltroDataFinal() {
        return filtroDataFinal;
    }

    public void setFiltroDataFinal(String filtroDataFinal) {
        this.filtroDataFinal = filtroDataFinal;
    }

    public String getFiltroHoraInicial() {
        return filtroHoraInicial;
    }

    public void setFiltroHoraInicial(String filtroHoraInicial) {
        this.filtroHoraInicial = filtroHoraInicial;
    }

    public String getFiltroHoraFinal() {
        return filtroHoraFinal;
    }

    public void setFiltroHoraFinal(String filtroHoraFinal) {
        this.filtroHoraFinal = filtroHoraFinal;
    }

    public int getFiltroMedico() {
        return filtroMedico;
    }

    public void setFiltroMedico(int filtroMedico) {
        this.filtroMedico = filtroMedico;
    }

    public int getFiltroPaciente() {
        return filtroPaciente;
    }

    public void setFiltroPaciente(int filtroPaciente) {
        this.filtroPaciente = filtroPaciente;
    }

    public TipoConsulta getFiltroTipo() {
        return filtroTipo;
    }

    public void setFiltroTipo(TipoConsulta filtroTipo) {
        this.filtroTipo = filtroTipo;
    }

    public List<Consulta> getLista_filtrada() {
        return lista_filtrada;
    }

    public void setLista_filtrada(List<Consulta> lista_filtrada) {
        this.lista_filtrada = lista_filtrada;
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
        this.lista_filtrada = ConsultaController.getInstance().relatorioConsulta(filtroDataInicial, filtroDataFinal, 
                        filtroHoraInicial, filtroHoraFinal, filtroMedico, filtroPaciente, filtroTipo);
        return this.lista_filtrada;
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
    
    public String relatorioFiltrado(ServletRequest request){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            this.lista_filtrada = null;
            if(u != null){
                this.lista_filtrada = ConsultaController.getInstance().relatorioConsulta(filtroDataInicial, filtroDataFinal, 
                        filtroHoraInicial, filtroHoraFinal, filtroMedico, filtroPaciente, filtroTipo);
                return "SECRETARIA_resultadorelatorio.xhtml";
            }else{
                return "index.xhtml";
            }
        }catch(Exception e){
            return "index.xhtml";
        }
    }
    
    public List<Consulta> resultadoPesquisa(ServletRequest request){
        try{
            return this.lista_filtrada;
        }catch(Exception e){
            return null;
        }
    }
    
    public List<Consulta> consultaDia(ServletRequest request){
        try{
            Calendar calendar = Calendar.getInstance();
            Date data = new Date(System.currentTimeMillis());
            calendar.setTime(data);  
            Date hoje = calendar.getTime(); 
            hoje.setHours(0);
            hoje.setMinutes(0);
            hoje.setSeconds(0);
            calendar.setTime(data);  
            Date hoje_noite = calendar.getTime(); 
            hoje_noite.setHours(23);
            hoje_noite.setMinutes(59);
            
            String data_incial = null;        
            String[] datahora = null;
            SimpleDateFormat formatDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm");        
            data_incial = formatDateTime.format(hoje);
            datahora = data_incial.split(" ");
            
            String data_final = null;        
            String[] datahora_final = null;       
            data_final = formatDateTime.format(hoje_noite);
            datahora_final = data_final.split(" ");
            this.lista_filtrada = ConsultaController.getInstance().relatorioConsulta(datahora[0], datahora_final[0], 
                        datahora[1], datahora_final[1], 0, 0, null);
            return this.lista_filtrada;
        }catch(Exception e){
            return null;
        }
    }
}
