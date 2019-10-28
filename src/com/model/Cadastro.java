package com.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Cadastro {

	@Id
	private String senha;
	private String nome;
	private String ddd;
	private Integer numero;
	private String telefone1;
	private String telefone2;
	private String email;
	private String tipo;
	
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
	public String getLogo() {
		return tipo;
	}
	public void settipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
