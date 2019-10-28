package com.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Tipo extends Cadastro {
	
    @OneToMany(fetch=FetchType.EAGER,mappedBy="celular", orphanRemoval=true)
	private Set<Usuario> residenciais;

	public Set<Usuario> getResidenciais() {
		return residenciais;
	}

	public void setResidenciais(Set<Usuario> residenciais) {
		this.residenciais = residenciais;
	}
	
}
