/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.controller;

import br.uem.din.clinicamedica.model.Prontuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaov
 */
public class ProntuarioController {
    private List<Prontuario> prontuario;
    
    private static ProntuarioController instance;

    public ProntuarioController() {
        this.prontuario = new ArrayList<>();
        //this.prontuario = Prontuario.povoarProntuario();
    }
    
    public static ProntuarioController getInstance() {
        if (instance == null) {
            instance = new ProntuarioController();
        }
        return instance;
    }
    
    public void salvarUsuario(Prontuario prontuario){
        this.prontuario.add(prontuario);
    }
    
    public List<Prontuario> listarProntuarios(){
        return this.prontuario;
    }
    
    public void salvarProntuario(Prontuario p){
        this.prontuario.add(p);
    }
}
