/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.bean;

import br.uem.din.clinicamedica.controller.ConsultaController;
import br.uem.din.clinicamedica.model.Consulta;
import br.uem.din.clinicamedica.model.Paciente;
import br.uem.din.clinicamedica.model.Usuario;
import br.uem.din.clinicamedica.model.utils.TipoConsulta;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
    private Date dataHora;
    private Usuario medico;
    private Paciente paciente;
    private TipoConsulta tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Usuario getMedico() {
        return medico;
    }

    public void setMedico(Usuario medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public TipoConsulta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConsulta tipo) {
        this.tipo = tipo;
    }
    
    public List<Consulta> listarConsultas(){
        return ConsultaController.getInstance().listarConsultas();
    }
    
    public String consultasMedicas(ServletRequest request){
        HttpSession sess = ((HttpServletRequest) request).getSession(true);
        Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
        return "consultaMedica";
    }
}
