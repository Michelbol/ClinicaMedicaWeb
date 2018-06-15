/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.model;
import br.uem.din.clinicamedica.controller.PacienteController;
import br.uem.din.clinicamedica.controller.UsuarioController;
import br.uem.din.clinicamedica.model.utils.TipoConsulta;
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

    public Date getDataHora() {
        return dataHora;
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
        c.dataHora = stringToDateTime("14/06/2018 08:00");
        c.id = lista_consultas.size()+1;
        c.medico = UsuarioController.getInstance().listarUsuarios().get(3);
        c.paciente = PacienteController.getInstance().listarPacientes().get(1);
        c.tipo = TipoConsulta.Normal;
        lista_consultas.add(c);
        c = new Consulta();
        c.dataHora = stringToDateTime("20/06/2018 10:00");
        c.id = lista_consultas.size()+1;
        c.medico = UsuarioController.getInstance().listarUsuarios().get(3);
        c.paciente = PacienteController.getInstance().listarPacientes().get(1);
        c.tipo = TipoConsulta.Retorno;
        lista_consultas.add(c);
        return lista_consultas;
    }
}
