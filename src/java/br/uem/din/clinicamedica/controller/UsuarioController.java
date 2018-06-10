/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.controller;

import br.uem.din.clinicamedica.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miche
 */
public class UsuarioController {
    private List<Usuario> usuarios;
    
    private static UsuarioController instance;

    public UsuarioController() {
        this.usuarios = new ArrayList<>();
        this.usuarios = Usuario.povoarUsuarios();
    }
    
    public static UsuarioController getInstance() {
        if (instance == null) {
            instance = new UsuarioController();
        }
        return instance;
    }
    
    public void salvarUsuario(Usuario usuario){
        this.usuarios.add(usuario);
    }
    
    public List<Usuario> listarUsuarios(){
        return this.usuarios;
    }
    
    public Usuario logar(Usuario u){
        try{
            int index = usuarios.indexOf(u);
            return usuarios.get(index);
        }catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
}
