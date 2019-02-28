package com.ro8.varastosofta.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Myyntiedustaja")
public class Myyntiedustaja {
	
	@Id
	@Column(name ="id")
	private int id;
	
	@Column(name ="tyontekija_id")
	private Tyontekija tyontekija;
	
	@Column(name ="palkkio")
	private double palkkio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tyontekija getTyontekija() {
		return tyontekija;
	}

	public void setTyontekija(Tyontekija tyontekija) {
		this.tyontekija = tyontekija;
	}

	public double getPalkkio() {
		return palkkio;
	}

	public void setPalkkio(double palkkio) {
		this.palkkio = palkkio;
	}

}
