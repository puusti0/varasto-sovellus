package com.ro8.varastosofta.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tyontekija")
public class Tyontekija {

	@Id
	@Column(name ="id")
	private int id;
	
	@Column(name ="yritys_id")
	private Yritys yritys;
	
	@Column(name ="nimi")
	private String nimi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Yritys getYritys() {
		return yritys;
	}

	public void setYritys(Yritys yritys) {
		this.yritys = yritys;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
}
