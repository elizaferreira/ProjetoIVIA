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
	private Set<Usuario> filiais;

	public Set<Usuario> getFiliais() {
		return filiais;
	}

	public void setFiliais(Set<Usuario> filiais) {
		this.filiais = filiais;
	}
	
}
