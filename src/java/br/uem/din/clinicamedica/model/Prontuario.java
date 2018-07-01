/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author miche
 */
public class Prontuario {
    private int id;
    private Paciente paciente;
    private Usuario medico;
    private String sintomas;
    private String diagnostico;
    private String prescricao;
    private Date data;

    public Prontuario(Paciente paciente, Usuario medico, String sintomas, String diagnostico, String prescricao, Date data) {
        this.paciente = paciente;
        this.medico = medico;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;
        this.data = data;
    }
    
    public Prontuario(int id, Paciente paciente, String sintomas, String diagnostico, String prescricao, Date data) {
        this.id = id;
        this.paciente = paciente;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public Usuario getMedico() {
        return medico;
    }

    public void setMedico(Usuario medico) {
        this.medico = medico;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public Prontuario() {
        
    }

    @Override
    public String toString() {
        return "Prontuario{" + "Paciente=" + paciente + ", Medico=" + medico + ", sintomas=" + sintomas + ", diagnostico=" + diagnostico + ", prescricao=" + prescricao + '}';
    }
    
    public static Date stringToDateTime(String data) {
        System.out.println("Data: "+data);
        String[] dateTime = data.split(" ");
        String[] date = dateTime[0].split("/");
        String[] time = dateTime[1].split(":");
        int dia = Integer.parseInt(date[0]);
        int mes = Integer.parseInt(date[1]) - 1;
        int ano = Integer.parseInt(date[2]);
        int hora = Integer.parseInt(time[0]);
        int minuto = Integer.parseInt(time[1]);
        if (ano > 99) {
            ano = ano - 1900;
        }
        if (ano < 50) {
            ano = ano + 2000;
        }
        Date dt = new Date(ano, mes, dia, hora, minuto);
        return dt;
    }
    
    public String getDataHora() {
        String data = null;        
        String[] datahora = null;
        SimpleDateFormat formatDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm");        
        if(this.data != null ){
            data = formatDateTime.format(this.data);
            datahora = data.split(" ");
        }
        return datahora[0] + " " + datahora[1];
    }
}
