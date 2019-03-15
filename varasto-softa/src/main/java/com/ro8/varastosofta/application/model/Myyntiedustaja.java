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

	/**
	 * get-metodi Id:lle
	 */
	public int getId() {
		return id;
	}

	/**
	 * set-metodi Id:lle
	 * @param id uusi id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get-metodi työntekijälle
	 */
	public Tyontekija getTyontekija() {
		return tyontekija;
	}

	/**
	 * set-metodi työntekijälle
	 * @param työntekijä asetettava työntekijä
	 */
	public void setTyontekija(Tyontekija tyontekija) {
		this.tyontekija = tyontekija;
	}

	/**
	 * get-metodi palkkiolle
	 */
	public double getPalkkio() {
		return palkkio;
	}

	/**
	 * set-metodi palkkiolle
	 * @param palkkio asetettava palkkio
	 */
	public void setPalkkio(double palkkio) {
		this.palkkio = palkkio;
	}

}
