/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.model.utils;

import br.uem.din.clinicamedica.model.Consulta;
import br.uem.din.clinicamedica.model.Paciente;
import br.uem.din.clinicamedica.model.Prontuario;
import br.uem.din.clinicamedica.model.Usuario;
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
            return "MEDICO/consultapacientes";
        }
        
        public void menuProntuario(List<Prontuario> li_prontuario,List<Paciente> li_paciente,Usuario usuarioLogado){
            
        }

        @Override
        public Paciente menuCadastrarPaciente() {
            return null;
        }

        @Override
        public Paciente menuAlteracoesPaciente(Paciente p) {
            return null;
        }

        @Override
        public Paciente menuSalvarAtributo(int i, Paciente p) {
            return null;
        }

        @Override
        public Paciente menuAtualizarAtributo(int i, Paciente p) {
            return null;
        }
      
    }, Secretaria{
        @Override
        public String menu() {
            return "SECRETARIA/menu";
        }

        @Override
        public String menuPaciente() {
            return "SECRETARIA/consultapaciente";
        }

        @Override
        public Paciente menuCadastrarPaciente() {
           return null;
        }

        @Override
        public Paciente menuAlteracoesPaciente(Paciente p) {
            return null;
        }

        @Override
        public Paciente menuSalvarAtributo(int i, Paciente p) {
            return null;
        }

        @Override
        public Paciente menuAtualizarAtributo(int i, Paciente p) {
            return null;
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
}
