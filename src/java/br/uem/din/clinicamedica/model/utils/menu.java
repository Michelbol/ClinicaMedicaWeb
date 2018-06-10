/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.model.utils;


import br.uem.din.clinicamedica.model.Paciente;

/**
 *
 * @author miche
 */
public interface menu {
    
    public abstract String menu();
    
    public abstract String menuPaciente();
    
    public abstract Paciente menuCadastrarPaciente();
    
    public abstract Paciente menuAlteracoesPaciente(Paciente p);
    
    public abstract Paciente menuSalvarAtributo(int i, Paciente p);
    
    public abstract Paciente menuAtualizarAtributo(int i, Paciente p);
}
