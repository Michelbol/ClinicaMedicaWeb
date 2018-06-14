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
import br.uem.din.clinicamedica.model.utils.Utils;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
    private String dataNascimento;    
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String residencialCodigoPais;
    private String residencialCodigoEstado;
    private String residencialPrefixo;
    private String residencialSufixo;
    private String celularcodiGoPais;
    private String celularcodiGoEstado;
    private String celularPrefixo;
    private String celularSufixo;
    private String email;
    private TipoConvenio tipoconvenio;
    private boolean isFumante;
    private boolean isAlcolatra;
    private boolean isColesterol;
    private boolean isDiabetico;
    private String doencasCardiacas;
    private String cirurgias;
    private String alergias;

    public void preencherBean(Paciente p) {
        this.id = p.getId();
        this.nome = p.getNome();
        this.sobrenome = p.getSobrenome();
        this.cpf = p.getCpf();
        this.rg = p.getRg();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.dataNascimento = sdf.format(p.getDataNascimento());
        this.logradouro = p.getEndereco().getLogradouro();
        this.numero = p.getEndereco().getNumero();
        this.bairro = p.getEndereco().getBairro();
        this.cidade = p.getEndereco().getCidade().getCidade();
        this.estado = p.getEndereco().getCidade().getEstado();
        this.pais = p.getEndereco().getCidade().getPais();
        this.residencialCodigoPais = p.getResidencial().getCodigoPais();
        this.residencialCodigoEstado = p.getResidencial().getCodigoEstado();
        this.residencialPrefixo = p.getResidencial().getPrefixo();
        this.residencialSufixo = p.getResidencial().getSufixo();
        this.celularcodiGoPais = p.getCelular().getCodigoEstado();
        this.celularcodiGoEstado = p.getCelular().getCodigoEstado();
        this.celularPrefixo = p.getCelular().getPrefixo();
        this.celularSufixo = p.getCelular().getSufixo();
        this.email = p.getEmail();
        this.tipoconvenio = p.getTipoconvenio();
        this.isFumante = p.isIsFumante();
        this.isAlcolatra = p.isIsAlcolatra();
        this.isColesterol = p.getColesterol();
        this.isDiabetico = p.isIsDiabetico();
        this.doencasCardiacas = p.getDoencasCardiacas();
        this.cirurgias = p.getCirurgias();
        this.alergias = p.getAlergias();
    }

    public PacienteBean() {
    }

    
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getResidencialCodigoPais() {
        return residencialCodigoPais;
    }

    public void setResidencialCodigoPais(String residencialCodigoPais) {
        this.residencialCodigoPais = residencialCodigoPais;
    }

    public String getResidencialCodigoEstado() {
        return residencialCodigoEstado;
    }

    public void setResidencialCodigoEstado(String residencialCodigoEstado) {
        this.residencialCodigoEstado = residencialCodigoEstado;
    }

    public String getResidencialPrefixo() {
        return residencialPrefixo;
    }

    public void setResidencialPrefixo(String residencialPrefixo) {
        this.residencialPrefixo = residencialPrefixo;
    }

    public String getResidencialSufixo() {
        return residencialSufixo;
    }

    public void setResidencialSufixo(String residencialSufixo) {
        this.residencialSufixo = residencialSufixo;
    }

    public String getCelularcodiGoPais() {
        return celularcodiGoPais;
    }

    public void setCelularcodiGoPais(String celularcodiGoPais) {
        this.celularcodiGoPais = celularcodiGoPais;
    }

    public String getCelularcodiGoEstado() {
        return celularcodiGoEstado;
    }

    public void setCelularcodiGoEstado(String celularcodiGoEstado) {
        this.celularcodiGoEstado = celularcodiGoEstado;
    }

    public String getCelularPrefixo() {
        return celularPrefixo;
    }

    public void setCelularPrefixo(String celularPrefixo) {
        this.celularPrefixo = celularPrefixo;
    }

    public String getCelularSufixo() {
        return celularSufixo;
    }

    public void setCelularSufixo(String celularSufixo) {
        this.celularSufixo = celularSufixo;
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
    
    public String incluir(ServletRequest request){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            return u.getTipo().incluir(); 
        }catch(Exception e){
            return "/index";
        }       
    }
    
    public String editar(ServletRequest request, Paciente pi){
        try{
            HttpSession sess = ((HttpServletRequest) request).getSession(true);
            Usuario u = (Usuario) sess.getAttribute("UsuarioLogado");
            Paciente p = PacienteController.getInstance().findPaciente(pi.getId());
            preencherBean(p);
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
   
    public String salvar(){
        PacienteController.getInstance().salvarPaciente(new Paciente(tipoconvenio, isFumante, isAlcolatra, isColesterol, isDiabetico, doencasCardiacas, cirurgias, alergias, nome, sobrenome, cpf, rg, Utils.stringToDate(dataNascimento), new Endereco(new Cidade(cidade, estado, "Brasil"), logradouro, numero, bairro), new Telefone("55", residencialCodigoEstado, residencialPrefixo, residencialSufixo), new Telefone("55", celularcodiGoEstado, celularPrefixo, celularSufixo), email));
        return "consultapaciente";
    }
}
