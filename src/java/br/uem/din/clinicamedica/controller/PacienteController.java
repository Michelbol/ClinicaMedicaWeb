/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.controller;

import br.uem.din.clinicamedica.model.Paciente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miche
 */
public class PacienteController {
    private List<Paciente> pacientes;
    
    private static PacienteController instance;

    public PacienteController() {
        this.pacientes = new ArrayList<>();
        this.pacientes = Paciente.povoarPacientes();
    }
    
    public static PacienteController getInstance() {
        if (instance == null) {
            instance = new PacienteController();
        }
        return instance;
    }
    
    public void salvarPaciente(Paciente paciente){
        int id = this.pacientes.size()+1;
        while(findPaciente(id) != null){
           id += 1;
        }
        paciente.setId(id);
        this.pacientes.add(paciente);
    }
    
    public List<Paciente> listarPacientes(){
        return this.pacientes;
    }
    
    public List<Paciente> pesquisarPacientes(String nome){
        List<Paciente> paciente_filtrado = new ArrayList();
        for(Paciente p : this.pacientes){
            if(nome == null || nome.length() == 0){
                paciente_filtrado.add(p);
            }else{
               if(p.getNome().toLowerCase().contains(nome.toLowerCase())){
                    paciente_filtrado.add(p);
                } 
            }
            
        }
        return paciente_filtrado;
    }
    
    public Paciente findPaciente(int id){
        for(Paciente p : this.pacientes){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
    
    public void atualizarPaciente(Paciente paciente){
        Paciente p2 = this.findPaciente(paciente.getId());
        excluirPaciente(p2.getId());
        this.pacientes.add(paciente);
    }
    
    public boolean excluirPaciente(int id){
        int i =0;
        for(Paciente p : this.pacientes){
            if(p.getId() == id){
                this.pacientes.remove(i);
                return true;
            }
            i+=1;
        }
        return false;
    }
}
