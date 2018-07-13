/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.controller;

import br.uem.din.clinicamedica.model.Consulta;
import br.uem.din.clinicamedica.model.Paciente;
import br.uem.din.clinicamedica.model.Usuario;
import br.uem.din.clinicamedica.model.utils.TipoConsulta;
import br.uem.din.clinicamedica.model.utils.Utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author miche
 */
public class ConsultaController {
    private List<Consulta> consultas;
    
    private static ConsultaController instance;

    public ConsultaController() {
        this.consultas = new ArrayList<>();
        this.consultas = Consulta.povoarConsulta();
    }
    
    public static ConsultaController getInstance() {
        if (instance == null) {
            instance = new ConsultaController();
        }
        return instance;
    }
    
    public void salvarConsulta(Consulta consulta){
        int id = this.consultas.size()+1;
        while(findConsulta(id) != null){
           id += 1;
        }
        consulta.setId(id);
        this.consultas.add(consulta);
    }
    
    public List<Consulta> listarConsultas(){
        return this.consultas;
    }
    
    public Consulta findConsulta(int id){
        for(Consulta p : this.consultas){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
    
    public void atualizarConsulta(Consulta consulta){
        Consulta c2 = this.findConsulta(consulta.getId());
        excluirConsulta(c2.getId());
        this.consultas.add(consulta);
    }
    
    public boolean excluirConsulta(int id){
        int i =0;
        for(Consulta p : this.consultas){
            if(p.getId() == id){
                this.consultas.remove(i);
                return true;
            }
            i+=1;
        }
        return false;
    }
    
    public List<Consulta> relatorioConsulta(String filtroDataInicial, String filtroDataFinal, String filtroHoraInicial,
            String filtroHoraFinal, int filtroMedico, int filtroPaciente, TipoConsulta filtroTipo){
        List<Consulta> consulta_filtrada = new ArrayList();
        Usuario medicoFiltrado = null;
        Paciente pacienteFiltrado = null;
            for(Consulta c : this.consultas){
                if(filtroHoraInicial == null){
                    filtroHoraInicial = "";
                }
                if(filtroDataInicial == null){
                    filtroDataInicial = "";
                }
                if(filtroHoraFinal == null){
                    filtroHoraFinal = "";
                }
                if(filtroDataFinal == null){
                    filtroDataFinal = "";   
                }
                if(filtroMedico > 0){
                    medicoFiltrado = UsuarioController.getInstance().findUsuario(filtroMedico);
                }
                if(filtroPaciente > 0){
                    pacienteFiltrado = PacienteController.getInstance().findPaciente(filtroPaciente);
                }
                if(filtroHoraInicial.length() == 0){
                    filtroHoraInicial = "00:00";
                }
                if(filtroDataInicial.length() == 0){
                    filtroDataInicial = "01/01/51";
                }
                if(filtroHoraFinal.length() == 0){
                    filtroHoraFinal = "23:59";
                }
                if(filtroDataFinal.length() == 0){
                    filtroDataFinal = "31/12/2100";
                }
                 
                if(((filtroMedico == 0) ? true : (c.getMedico() == medicoFiltrado))&& 
                        ((filtroPaciente == 0) ? true : (pacienteFiltrado == c.getPaciente()))&& 
                        ((
                            TipoConsulta.Normal.equals(filtroTipo) || (TipoConsulta.Retorno.equals(filtroTipo))) ?
                            (filtroTipo == c.getTipo()) : true
                        )&&
                        (c.getDataHoraDate().after(Utils.stringToDateTime(filtroDataInicial+" "+filtroHoraInicial)))&&
                        (c.getDataHoraDate().before(Utils.stringToDateTime(filtroDataFinal+" "+filtroHoraFinal)))){
                    consulta_filtrada.add(c);
                }
            }
            return consulta_filtrada;
    }
}
