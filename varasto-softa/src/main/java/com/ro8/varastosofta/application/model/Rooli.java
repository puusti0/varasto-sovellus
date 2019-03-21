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
	
	@Column(name ="Nimi")
	private String nimi;
	
	@OneToMany(mappedBy= "id")
	private List<Kayttaja> kayttajat;
	
	/**
	 * Luokan Rooli konstruktori ilman parametreja
	 */
	public Rooli() {
		
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
