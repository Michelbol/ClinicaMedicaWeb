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
            return "MEDICO/menu";
        }

        @Override
        public String menuPaciente() {
            return "MEDICO/consultapaciente.xhtml";
        }
        
        @Override
        public String incluir() {
           return "incluir";
        }

        @Override
        public String editar(int i) {
            return "excluir";
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
            return "/SECRETARIA/menu";
        }

        @Override
        public String menuPaciente() {
            return "SECRETARIA/consultapaciente.xhtml";
        }

        @Override
        public String incluir() {
           return "incluir";
        }

        @Override
        public String editar(int i) {
            return "editar";
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
        System.out.println(dt.toString());
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
        return "MEDICO/consultaprontuario.xhtml";
    }
    
    public String menuRelatorios(){
        return "MEDICO/consultarelatorios.xhtml";
    }
    
    public String incluirProntuario(){
        return "incluirProntuario.xhtml";
    }
    
    public String editarProntuario(long id){
        return "editarProntuario.xhtml";
    }
}
