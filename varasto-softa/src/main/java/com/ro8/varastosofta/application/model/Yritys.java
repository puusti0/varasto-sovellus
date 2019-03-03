package com.ro8.varastosofta.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Yritys")
public class Yritys {

	@Id
	@Column(name ="id")
	private int id;
	
	@Column(name ="nimi")
	private String nimi;

	public Yritys() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
}
