/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.model.utils;

/**
 *
 * @author miche
 */
public interface menu {
    
    public abstract String menu();
    
    public abstract String menuPaciente();
    
    public abstract String incluir();
    
    public abstract String editar(int id);
    
    public abstract void salvar();
    
    public abstract void atualizar(int id);
    
    public abstract String excluir(int id);
}
