/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.clinicamedica.bean;

/**
 *
 * @author miche
 */

import br.uem.din.clinicamedica.controller.PacienteController;
import br.uem.din.clinicamedica.model.Paciente;
import br.uem.din.clinicamedica.model.Usuario;
import br.uem.din.clinicamedica.model.utils.Cidade;
import br.uem.din.clinicamedica.model.utils.Endereco;
import br.uem.din.clinicamedica.model.utils.Telefone;
import br.uem.din.clinicamedica.model.utils.TipoConvenio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "PacienteBean")
@RequestScoped
public class PacienteBean {
    private int id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String rg;
    private Date dataNascimento;
    private Endereco endereco;
    private Telefone residencial;
    private Telefone celular;
    private String email;
    private TipoConvenio tipoconvenio;
    private boolean isFumante;
    private boolean isAlcolatra;
    private boolean isColesterol;
    private boolean isDiabetico;
    private String doencasCardiacas;
    private String cirurgias;
    private String alergias;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getResidencial() {
        return residencial;
    }

    public void setResidencial(Telefone residencial) {
        this.residencial = residencial;
    }

    public Telefone getCelular() {
        return celular;
    }

    public void setCelular(Telefone celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoConvenio getTipoconvenio() {
        return tipoconvenio;
    }

    public void setTipoconvenio(TipoConvenio tipoconvenio) {
        this.tipoconvenio = tipoconvenio;
    }

    public boolean isIsFumante() {
        return isFumante;
    }

    public void setIsFumante(boolean isFumante) {
        this.isFumante = isFumante;
    }

    public boolean isIsAlcolatra() {
        return isAlcolatra;
    }

    public void setIsAlcolatra(boolean isAlcolatra) {
        this.isAlcolatra = isAlcolatra;
    }

    public boolean isIsColesterol() {
        return isColesterol;
    }

    public void setIsColesterol(boolean isColesterol) {
        this.isColesterol = isColesterol;
    }

    public boolean isIsDiabetico() {
        return isDiabetico;
    }

    public void setIsDiabetico(boolean isDiabetico) {
        this.isDiabetico = isDiabetico;
    }

    public String getDoencasCardiacas() {
        return doencasCardiacas;
    }

    public void setDoencasCardiacas(String doencasCardiacas) {
        this.doencasCardiacas = doencasCardiacas;
    }

    public String getCirurgias() {
        return cirurgias;
    }

    public void setCirurgias(String cirurgias) {
        this.cirurgias = cirurgias;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    
    

    public String consultaPacientes(ServletRequest request){
        HttpSession sess = ((HttpServletRequest) request).getSession(true);
        Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
        return u.getTipo().menuPaciente();
    }
    
    public List<Paciente> listarPacientes(){
        return PacienteController.getInstance().listarPacientes();
    }
    
    public ArrayList<SelectItem> getSelectPaciente(){
        ArrayList lista = new ArrayList();
        List<Paciente> pacientes = PacienteController.getInstance().listarPacientes();
        
        lista.add(new SelectItem(null,"Selecione um paciente..."));
        
        for(Paciente p: pacientes){
            lista.add(new SelectItem(p,p.getNome()));
        }
        return lista;
    }
    
    public String incluir(ServletRequest request){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            return u.getTipo().incluir(); 
        }catch(Exception e){
            return "/index";
        }       
    }
    
    public String editar(ServletRequest request, int id){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            return u.getTipo().editar(id);
        }catch(Exception e){
            return "/index";
        } 
    }
    
    public String excluir(ServletRequest request, int id){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            return u.getTipo().excluir(id);
        }catch(Exception e){
            return "/index";
        }         
    }
    
    public TipoConvenio[] tipoconvenios(){
        return TipoConvenio.values();
    }
    
    public void salvar(){
        PacienteController.getInstance().salvarUsuario(new Paciente(tipoconvenio, isFumante, isAlcolatra, isColesterol, isDiabetico, doencasCardiacas, cirurgias, alergias, nome, sobrenome, cpf, rg, dataNascimento, new Endereco(new Cidade(endereco.getCidade().getCidade(), endereco.getCidade().getEstado(), "Brasil"), nome, nome, rg), residencial, celular, email));
    }
}
