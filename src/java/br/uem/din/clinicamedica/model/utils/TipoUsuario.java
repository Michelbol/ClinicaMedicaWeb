/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.model.utils;

import br.uem.din.clinicamedica.model.Consulta;
import br.uem.din.clinicamedica.model.Paciente;
import java.util.Date;
import java.util.List;

/**
 *
 * @author miche
 */
public enum TipoUsuario implements menu{
    Medico{
        @Override
        public String menu() {
            return "MEDICO_menu.xhtml";
        }

        @Override
        public String menuPaciente() {
            return "consultaPaciente.xhtml";
        }
        
        @Override
        public String incluir() {
           return "MEDICO_incluirPaciente.xhtml";
        }

        @Override
        public String editar(int i) {
            return "MEDICO_editarPaciente.xhtml";
        }

        @Override
        public void salvar(){
            
        }

        @Override
        public void atualizar(int id){
            
        }
        
        @Override
        public String excluir(int id){
            return "excluir";
        }
      
    }, Secretaria{
        @Override
        public String menu() {
            return "SECRETARIA_menu.xhtml";
        }

        @Override
        public String menuPaciente() {
            return "consultaPaciente.xhtml";
        }

        @Override
        public String incluir() {
           return "SECRETARIA_incluirPaciente.xhtml";
        }

        @Override
        public String editar(int i) {
            return "SECRETARIA_editarPaciente.xhtml";
        }

        @Override
        public void salvar(){
            
        }

        @Override
        public void atualizar(int id){
            
        }
        
        @Override
        public String excluir(int id){
            return "excluir";
        }
        
    };
    
    public Date stringToDate(String data) {
        String[] g = data.split("/");
        int dia = Integer.parseInt(g[0]);
        int mes = Integer.parseInt(g[1]) - 1;
        int ano = Integer.parseInt(g[2]);
        if (ano > 99) {
            ano = ano - 1900;
        }
        if (ano < 50) {
            ano = ano + 2000;
        }
        Date dt = new Date(ano, mes, dia);        
        return dt;
    }
    
    public void alterarPaciente(List<Paciente> li) {
        
    }

    public void relatoriosConsultas(List<Consulta> lista_consultas) {
        
    }
    
    public static boolean removerPaciente(List<Paciente> lista_pacientes) {
        
        return true;
    }
    public String menuProntuario(){
        return "MEDICO_consultaProntuario.xhtml";
    }
    
    public String menuRelatorios(){
        return "MEDICO_consultaRelatorios.xhtml";
    }
    
    public String incluirProntuario(){
        return "MEDICO_incluirProntuario.xhtml";
    }
    
    public String editarProntuario(int id){
        return "MEDICO_editarProntuario.xhtml";
    }
}
