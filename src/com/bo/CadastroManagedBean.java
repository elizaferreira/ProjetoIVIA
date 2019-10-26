package com.bo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.dao.CadastroDAO;
import com.model.Cadastro;
import com.model.Usuario;
import com.model.Tipo;
import com.util.ImageUtil;
import com.validation.CadastroValidation;

public class CadastroManagedBean implements java.io.Serializable{
	
	private StreamedContent listImage = null;

	private static Logger log = Logger.getLogger(CadastroManagedBean.class);

	private static final long serialVersionUID = 1L;

	private String celular;
	
	private String selectedsenha;
	
	private String nome;
	private String senha;
	private Integer numero;
	private String telefone1;
	private String telefone2;
	private String email;
	private String ddd;
	private String msg;
	private String tipo;
	
	private Part logotipo;
	private String strLogotipo;
	
	private CadastroManagedBean cadastroBack;
	
	private boolean celularResidencial;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getsenha() {
		return senha;
	}
	public void setsenha(String Senha) {
		senha = Senha;
	}
	public String getDDD() {
		return ddd;
	}
	public void setDDD(String ddd) {
		this.ddd = ddd;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public boolean isCelularResidencial() {
		return celularResidencial;
	}
	public void setCelularResidencial(boolean celularResidencial) {
		this.celularResidencial = celularResidencial;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSelectedsenha() {
		return selectedsenha;
	}
	public void setSelectedsenha(String selectedsenha) {
		this.selectedsenha = selectedsenha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
    public String navigateCreate() {
    	return "insert";
    }

    public String navigateIndex() {
    	return "index";
    }
	public CadastroManagedBean getCadastroBack() {
		return cadastroBack;
	}
	public void setCadastroBack(CadastroManagedBean cadastroBack) {
		this.cadastroBack = cadastroBack;
	}
	public String getStrLogotipo() {
		return strLogotipo;
	}
	public void setStrLogotipo(String strLogotipo) {
		this.strLogotipo = strLogotipo;
	}
	
	public String selectToDelete(){
		
		this.getSelectedsenha();
		
		return "";
	}

    public void limparCadastro() {
    	
    	log.info("Limpando cadastro");
    	
    	this.setNome("");
    	this.setsenha("");
    	this.setDDD("");
    	this.setTelefone1("");
    	this.setTelefone2(""); 	
    	this.setEmail("");  	
    	this.setNumero(null);
    }

    public String editCadastro() {
    	log.info("Editando cadastro "+this.getSelectedsenha());
    	
		CadastroDAO cadastroDAO = new CadastroDAO();
		Cadastro cadastro = cadastroDAO.getCadastro(this.getSelectedsenha());
    	
    	if(cadastro!=null){
    		this.setsenha(cadastro.getsenha());
    		this.setNome(cadastro.getNome());
    		this.setEmail(cadastro.getEmail());
    		this.setDDD(cadastro.getDDD());
    		this.setTelefone1(cadastro.getTelefone1());
    		this.setTelefone2(cadastro.getTelefone2());
    		this.setNumero(cadastro.getNumero());
    		
    		if(cadastro instanceof Tipo){
    			this.setCelularResidencial(true);
    		}else if(cadastro instanceof Usuario){
    			this.setCelularResidencial(false);
    			this.setCelular(((Usuario)cadastro).getCelular().getsenha());
    		}
    	}else{
    		this.setMsg("Cadastro nao encontrada!");
    		log.error("Cadastro nao encontrada!");
    	}
		
		
    	return "update";
    }
    public void preencherCadastro() {
    	
    	log.info("Preenchendo cadastro " + this.getCadastroBack().getsenha());

		CadastroDAO cadastroDAO = new CadastroDAO();
    	Cadastro cadastro = cadastroDAO.getCadastro(this.getCadastroBack().getsenha());

    	if(cadastro!=null){
    		this.setsenha(cadastro.getsenha());
    		this.setNome(cadastro.getNome());
    		this.setEmail(cadastro.getEmail());
    		this.setDDD(cadastro.getDDD());
    		this.setTelefone1(cadastro.getTelefone1());
    		this.setTelefone2(cadastro.getTelefone2());
    		this.setNumero(cadastro.getNumero());
    		
    		if(cadastro instanceof Tipo){
    			this.setCelularResidencial(true);
    		}else if(cadastro instanceof Usuario){
    			this.setCelularResidencial(false);
    			this.setCelular(((Usuario)cadastro).getCelular().getsenha());
    		}

    	}else{
    		this.setMsg("Cadastro nao encontrada!");
    		log.error("Cadastro nao encontrada!");
    	}
    }
		
    public String createCadastro() {

    	String str = "index";
    	
    	try{
    		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
    		
        	try{
        		this.setStrLogotipo(ImageUtil.saveToDiskImage(logotipo.getInputStream(), logotipo.getSubmittedFileName(), path));
        	}catch(Exception e){
        		log.error(e);
        	}
    		Cadastro cadastroModel  =  CadastroValidation.transformBOModel(this);
    		
    		CadastroDAO cadastroDAO = new CadastroDAO();
    		cadastroDAO.createCadastro(cadastroModel); 
    		
    		limparCadastro();
    		this.setMsg("Cadastro cadastrada!");
    	}catch(Exception e){
    		this.setMsg(e.getMessage());
    		str = "insert";
    		log.error(e);
    	}
    	
    	return str;
    }

	public String deleteCadastro(){
    	
    	log.info("Excluindo cadastro "+this.getSelectedsenha());
    	
    	String str = "index";
    	
    	try{
    		CadastroDAO cadastroDAO = new CadastroDAO();
    		cadastroDAO.deleteCadastro(this.getSelectedsenha());
    		
    		limparCadastro();
    		this.setMsg("Excluído com sucesso!");
    		
    	}catch(Exception e){
    		this.setMsg(e.getMessage());
    		log.error(e);
    	}	

    	return str;
	}
	
	public List<CadastroManagedBean> setListaCelulares(){

    	log.info("Listando celulares");
    	
		List<CadastroManagedBean> cadastro = new ArrayList<CadastroManagedBean>();
		
    	try{
    		CadastroDAO cadastroDAO = new CadastroDAO();
    		
    		for(Tipo celular:cadastroDAO.listCelulares()){
    			cadastro.add(CadastroValidation.transformModelBO(celular,true));
    		}

    	}catch(Exception e){
    		this.setMsg(e.getMessage());
    		log.error(e);
    	}
    	
		return cadastro;
	}
	
	public List<CadastroManagedBean> getListaCadastros(){
		
		limparCadastro();
    	
    	log.info("Listando cadastro");
    	
		List<CadastroManagedBean> cadastros = new ArrayList<CadastroManagedBean>();
		
    	try{
    		CadastroDAO cadastroDAO = new CadastroDAO();
    		
    		for(Tipo celular:cadastroDAO.listCelulares()){
    			cadastros.add(CadastroValidation.transformModelBO(celular,true));
    			cadastros.addAll(CadastroValidation.transformModelBOListFiliais(celular.getFiliais()));
    		}

    	}catch(Exception e){
    		this.setMsg(e.getMessage());
    		log.error(e);
    	}
    	
		return cadastros;	
	}
	
	public String updateCadastro(){
    	String str = "index";
    	try{
    		CadastroDAO cadastroDAO = new CadastroDAO();
    			
    		Cadastro cadastroModel  =  CadastroValidation.transformBOModel(this);
    		
    		cadastroDAO.updateCadastro(cadastroModel);	

    		limparCadastro();
        	
    		this.setMsg("Atualizado com sucesso!");
    	}catch(Exception e){
    		this.setMsg(e.getMessage());
    		str = "deleteUpdate";
    		log.error(e);
    	}
    	
    	return str;
	}
	
    public StreamedContent getListImage() {
    	
    	FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
      
            return new DefaultStreamedContent();
        }
        else {
            try {
            	return new DefaultStreamedContent(new FileInputStream(strLogotipo));
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    	
        return listImage;
    }
}
