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

	/**
	 * get-metodi yrityksen id:lle
	 */
	public int getId() {
		return id;
	}

	/**
	 * set-metodi yrityksen id:lle
	 * @param id yrityksen id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get-metodi yrityksen nimelle
	 */
	public String getNimi() {
		return nimi;
	}

	/**
	 * set-metodi yrityksen nimelle
	 * @param nimi yrityksen nimi
	 */
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
}
