package com.ro8.varastosofta.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Kayttaja")
public class Kayttaja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="rooli_Id")
	private Rooli rooli;
	
	@Column(name ="kayttajatunnus")
	private String kayttajatunnus;
	
	@Column(name ="salasana")
	private String salasana;
	
	/**
	 * Luokan Kayttaja konstruktori ilman parametreja
	 */
	public Kayttaja() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rooli getRooli() {
		return rooli;
	}

	public void setRooli(Rooli rooli) {
		this.rooli = rooli;
	}

	public String getKayttajatunnus() {
		return kayttajatunnus;
	}

	public void setKayttajatunnus(String kayttajatunnus) {
		this.kayttajatunnus = kayttajatunnus;
	}

	public String getSalasana() {
		return salasana;
	}

	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}
	
}
