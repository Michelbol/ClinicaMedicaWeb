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
    
    public void salvarUsuario(Paciente paciente){
        this.pacientes.add(paciente);
    }
    
    public List<Paciente> listarPacientes(){
        return this.pacientes;
    }
}
