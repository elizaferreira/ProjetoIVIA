package com.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.bo.CadastroManagedBean;
import com.dao.CadastroDAO;
import com.model.Cadastro;
import com.model.Usuario;
import com.model.Tipo;

public class CadastroValidation {

	public static Cadastro transformBOModel(CadastroManagedBean bo) {

		Cadastro cadastro = new Tipo();
		
		if(!bo.isCelularResidencial()){
			cadastro = new Usuario();
			CadastroDAO dao = new CadastroDAO();
			
			((Usuario)cadastro).setCelular((Tipo)dao.getCadastro(bo.getCelular()));
		}
		
		cadastro.setsenha(bo.getsenha());

		cadastro.setNome(bo.getNome());
		cadastro.setEmail(bo.getEmail());
		cadastro.setDDD(bo.getDDD());
		cadastro.setTelefone1(bo.getTelefone1());
		cadastro.setTelefone2(bo.getTelefone2());
		cadastro.setNumero(bo.getNumero());
		cadastro.setLogotipo(bo.getStrLogotipo());
		//cadastro.setContrato(bo.getStrContrato());
		
		return cadastro;
	}
	
	public static CadastroManagedBean transformModelBO(Cadastro cadastro, boolean celularResidencial) {

		CadastroManagedBean bo = new CadastroManagedBean();
		
		bo.setsenha(cadastro.getsenha());
		bo.setNome(cadastro.getNome());
		bo.setEmail(cadastro.getEmail());
		bo.setDDD(cadastro.getDDD());
		bo.setTelefone1(cadastro.getTelefone1());
		bo.setTelefone2(cadastro.getTelefone2());
		bo.setNumero(cadastro.getNumero());
		
		bo.setStrLogotipo(cadastro.getLogotipo());
		
		if(celularResidencial){
			bo.setTipo("Celular");
		}else{
			bo.setTipo("Residencial");
		}
		
		return bo;
	}
	
	public static List<CadastroManagedBean> transformModelBOListFiliais(Set<Usuario> filiais) throws Exception {
		// TODO Auto-generated method stub
		List<CadastroManagedBean> cadastrosBO = new ArrayList<CadastroManagedBean>();
		
		for(Usuario residencial:filiais){
			CadastroManagedBean bo = new CadastroManagedBean();

			bo=transformModelBO(residencial, false);
			bo.setCelularResidencial(false);
			
			cadastrosBO.add(bo);		
		}
		
		return cadastrosBO;
	}
}
