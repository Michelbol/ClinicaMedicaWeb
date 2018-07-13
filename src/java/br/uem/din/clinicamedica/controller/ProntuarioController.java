/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.controller;

import br.uem.din.clinicamedica.model.Paciente;
import br.uem.din.clinicamedica.model.Prontuario;
import br.uem.din.clinicamedica.model.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaov
 */
public class ProntuarioController {
    private List<Prontuario> prontuario;
    
    private static ProntuarioController instance;

    public ProntuarioController() {
        this.prontuario = new ArrayList<>();
        this.prontuario = Prontuario.povoarProntuario();
    }
    
    public static ProntuarioController getInstance() {
        if (instance == null) {
            instance = new ProntuarioController();
        }
        return instance;
    }
    
    public void salvarUsuario(Prontuario prontuario){
        this.prontuario.add(prontuario);
    }
    
    public List<Prontuario> listarProntuarios(){
        return this.prontuario;
    }
    
    public void salvarProntuario(Prontuario prontuario){
        this.prontuario.add(prontuario);
        prontuario.setId(this.prontuario.size());
    }
        
    public void atualizarPaciente(Prontuario prontuario){
        Prontuario prontuario2 = this.findProntuario(prontuario.getId());
        prontuario.setMedico(prontuario2.getMedico());
        excluirProntuario(prontuario2.getId());
        this.prontuario.add(prontuario);
    }
    
    public Prontuario findProntuario(int id){
        for(Prontuario p : this.prontuario){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
    
    public boolean excluirProntuario(int id){
        int i =0;
        for(Prontuario p : this.prontuario){
            if(p.getId() == id){
                this.prontuario.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }
    
    public List<Prontuario> relatorioProntuario(String filtroDataInicial, String filtroDataFinal, String filtroHoraInicial,
            String filtroHoraFinal, int filtroPaciente){
        
        List<Prontuario> prontuario_filtrado = new ArrayList();
        
        Paciente pacienteFiltrado = null;
     
        try{
            for(Prontuario p : this.prontuario){
                if(filtroHoraInicial==null){
                    filtroHoraInicial = "";
                }
                if(filtroDataInicial==null){
                    filtroDataInicial = "";
                }
                if(filtroHoraFinal==null){
                    filtroHoraFinal = "";
                }
                if(filtroDataFinal==null){
                    filtroDataFinal = "";
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
                if(filtroPaciente>0) pacienteFiltrado = PacienteController.getInstance().findPaciente(filtroPaciente);
                 
                if((pacienteFiltrado==null||pacienteFiltrado.equals(p.getPaciente()))&&
                   (p.getData().after(Utils.stringToDateTime(filtroDataInicial+" "+filtroHoraInicial)))&&
                   (p.getData().before(Utils.stringToDateTime(filtroDataFinal+" "+filtroHoraFinal)))){
                    prontuario_filtrado.add(p);
                }
            }
            return prontuario_filtrado;
        }catch(Exception e){
            return null;
        }
    }        
}
