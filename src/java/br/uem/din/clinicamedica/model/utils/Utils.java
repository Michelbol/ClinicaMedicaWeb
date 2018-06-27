/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.model.utils;

import java.util.Date;

/**
 *
 * @author miche
 */
public class Utils {
    public static Date stringToDate(String data) {
        if(!(data.length() > 0)){
            return null;
        }
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
    
    public static Date stringToDateTime(String data) {
        if(data != null || data.length() > 0){
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
        }else{
            return null;
        }
    }

}
