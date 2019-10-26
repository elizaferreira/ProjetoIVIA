package com.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Usuario extends Cadastro {

	@ManyToOne(fetch = FetchType.EAGER)
	private Tipo celular;

	public Tipo getCelular() {
		return celular;
	}

	public void setCelular(Tipo celular) {
		this.celular = celular;
	}
}
