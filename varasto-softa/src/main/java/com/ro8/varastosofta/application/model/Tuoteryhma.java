package com.ro8.varastosofta.application.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Tuoteryhma")
public class Tuoteryhma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name ="Nimi")
	private String nimi;
	
	@OneToMany(mappedBy="tuoteryhma")
	private List<Tuote> tuotteet;
	
	/**
	 * Luokan Tuoteryhmä konstruktori ilman parametreja
	 */
	public Tuoteryhma() {
		
	}

	/**
	 * Luokan Tuoteryhma konstruktori parametreilla
	 * @param id tuoteryhmän id
	 * @param nimi tuoteryhmän nimi
	 */
	public Tuoteryhma(int id, String nimi){
		this.id = id;
		this.nimi = nimi;
	}

	/**
	 * get-metodi tuoteryhmän sisältämille tuotteille
	 */
	public List<Tuote> getTuotteet() {
		return tuotteet;
	}

	/**
	 * set-metodi tuoteryhmään lisättäville tuotteille
	 * @param tuotteet tuoteryhmään lisättävät tuotteet
	 */
	public void setTuotteet(List<Tuote> tuotteet) {
		this.tuotteet = tuotteet;
	}

	/**
	 * get-metodi tuoteryhmän id:lle
	 */
	public int getId() {
		return id;
	}

	/**
	 * set-metodi tuoteryhmän id:lle
	 * @param id tuoteryhmän id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get-metodi tuoteryhmän nimelle
	 */
	public String getNimi() {
		return nimi;
	}

	/**
	 * set-metodi tuoteryhmän nimelle
	 * @param nimi tuoteryhmän nimi
	 */
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
}
