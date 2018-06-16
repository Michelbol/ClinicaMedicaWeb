/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.model;
import br.uem.din.clinicamedica.controller.PacienteController;
import br.uem.din.clinicamedica.controller.UsuarioController;
import br.uem.din.clinicamedica.model.utils.TipoConsulta;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author miche
 */
public class Consulta {
    private int id;
    private Date dataHora;
    private Usuario medico;
    private Paciente paciente;
    private TipoConsulta tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataHora() {
        String dt = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        if(this.dataHora != null ){
            dt = sdf.format(this.dataHora);
        }
        return dt;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Usuario getMedico() {
        return medico;
    }

    public void setMedico(Usuario medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public TipoConsulta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConsulta tipo) {
        this.tipo = tipo;
    }

    public Consulta(Date dataHora, Usuario medico, Paciente paciente, TipoConsulta tipo) {
        this.dataHora = dataHora;
        this.medico = medico;
        this.paciente = paciente;
        this.tipo = tipo;
    }
    public Consulta(int id, Date dataHora, Usuario medico, Paciente paciente, TipoConsulta tipo) {
        this.id = id;
        this.dataHora = dataHora;
        this.medico = medico;
        this.paciente = paciente;
        this.tipo = tipo;
    }

    public Consulta() {
    }

    @Override
    public String toString() {
        return "Consulta{" + "dataHora=" + dataHora + ", medico=" + medico + ", paciente=" + paciente + ", tipo=" + tipo + '}';
    }
    
    public static Date stringToDateTime(String data) {
        System.out.println("Data: "+data);
        String[] dateTime = data.split(" ");
        System.out.println("DataTime: "+dateTime);
        String[] date = dateTime[0].split("/");
        System.out.println("date: "+date);
        String[] time = dateTime[1].split(":");
        System.out.println("Time: "+time);
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
        System.out.println(dt.toString());
        return dt;
    }
    
    public static List<Consulta> povoarConsulta(){
        List<Consulta> lista_consultas = new ArrayList();
        Consulta c = new Consulta();
        c.setDataHora(stringToDateTime("14/06/2018 08:00"));
        c.setId(1);
        c.setMedico(UsuarioController.getInstance().listarUsuarios().get(2));
        c.setPaciente(PacienteController.getInstance().listarPacientes().get(1));
        c.setTipo(TipoConsulta.Normal);
        lista_consultas.add(c);
        c = new Consulta();
        c.setDataHora(stringToDateTime("20/06/2018 10:00"));        
        c.setId(2);
        c.setMedico(UsuarioController.getInstance().listarUsuarios().get(2));
        c.setPaciente(PacienteController.getInstance().listarPacientes().get(1));
        c.setTipo(TipoConsulta.Retorno);
        lista_consultas.add(c); 
        return lista_consultas;
    }
}
