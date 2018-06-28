/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.bean;


import br.uem.din.clinicamedica.controller.UsuarioController;
import br.uem.din.clinicamedica.model.Usuario;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author miche
 */
@ManagedBean(name = "UsuarioBean")
@RequestScoped
public class UsuarioBean {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String logar(ServletRequest request){
        String usuarioLogado = "";
        usuarioLogado = this.login(request);
        if("index".equals(usuarioLogado)){
            Usuario u = new Usuario(username, password);
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            u = UsuarioController.getInstance().logar(u);
            if(u != null){
               sess.setAttribute("UsuarioLogado", u);
                return u.getTipo().menu();
           }else{
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Usuário ou senha inválidos"));
            return "index";
           }
        }else{
            return usuarioLogado;
        }
    }
    
    public String login(ServletRequest request){
        HttpSession sess = ((HttpServletRequest) request).getSession(true);
        Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
        if(u == null){
            return "index";
        }else{
            return u.getTipo().menu();
        }
    }
    
    public List<Usuario> listarUsuarios(){
        return UsuarioController.getInstance().listarUsuarios();
    }
    
    public String menu(ServletRequest request){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            if(u == null){
                FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Usuário não está logado!"));
                return "index.xhtml";
            }else{
                return u.getTipo().menu();
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Erro: "+e.getMessage()));
            return "index.xhtml";
        }
    }
    
    public String sair(ServletRequest request){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            sess.removeAttribute("UsuarioLogado");
            return "index.xhtml";
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login:username", new FacesMessage("Erro: "+e.getMessage()));
            return "index.xhtml";
        }
    }
}
