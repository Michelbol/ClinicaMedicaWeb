/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.controller;

import br.uem.din.clinicamedica.model.Consulta;
import java.util.ArrayList;
import java.util.List;

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
    
    public void atualizarConsulta(Consulta paciente){
        Consulta p2 = this.findConsulta(paciente.getId());
        excluirPaciente(p2.getId());
        this.consultas.add(paciente);
    }
    
    public boolean excluirPaciente(int id){
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
}
