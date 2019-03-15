package com.ro8.varastosofta.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Varastotyontekija")
public class Varastotyontekija {
	
	@Id
	@Column(name ="id")
	private int id;
	
	@Column(name ="tyontekija_id")
	private Tyontekija tyontekija;

	public Varastotyontekija() {
		
	}

	/**
	 * get-metodi varastotyöntekijän id:lle
	 */
	public int getId() {
		return id;
	}

	/**
	 * set-metodi varastotyöntekijän id:lle
	 * @param id varastotyöntekijän id
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
	 * @param tyontekija tyontekija
	 */
	public void setTyontekija(Tyontekija tyontekija) {
		this.tyontekija = tyontekija;
	}

}
