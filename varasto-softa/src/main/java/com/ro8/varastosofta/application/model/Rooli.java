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
@Table(name="Rooli")
public class Rooli {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name ="nimi")
	private String nimi;
	
	@OneToMany(mappedBy="rooli")
	private List<Kayttaja> kayttajat;
	
	/**
	 * Luokan Rooli konstruktori ilman parametreja
	 */
	public Rooli() {
		
	}
	
	/**
	 * Luokan Rooli konstruktori parametreilla
	 * @param id roolin id
	 * @param nimi roolin nimi
	 */
	public Rooli(int id, String nimi){
		this.id = id;
		this.nimi = nimi;
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

	public List<Kayttaja> getKayttajat() {
		return kayttajat;
	}

	public void setKayttajat(List<Kayttaja> kayttajat) {
		this.kayttajat = kayttajat;
	}
}
