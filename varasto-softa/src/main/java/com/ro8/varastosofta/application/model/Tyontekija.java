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

	public Tyontekija() {
		
	}

	/**
	 * get-metodi työntekijän id:lle
	 */
	public int getId() {
		return id;
	}

	/**
	 * set-metodi työntekijän id:lle
	 * @param id työntekijän id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get-metodi työntekijän firmalle
	 */
	public Yritys getYritys() {
		return yritys;
	}

	/**
	 * set-metodi työntekijän firmalle
	 * @param yritys työntekijän yritys
	 */
	public void setYritys(Yritys yritys) {
		this.yritys = yritys;
	}

	/**
	 * get-metodi työntekijän nimelle
	 */
	public String getNimi() {
		return nimi;
	}

	/**
	 * set-metodi työntekijän nimelle
	 * @param nimi työntekijän nimi
	 */
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
}
