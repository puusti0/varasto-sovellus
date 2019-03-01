package com.ro8.varastosofta.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tilausrivi")
public class Tilausrivi {
	
	@Id
	@Column(name ="id")
	private int id;
	
	@Column(name ="tilaus_id")
	private Tilaus tilaus;
	
	@Column(name ="tuote_id")
	private Tuote tuote;
	
	@Column(name ="lukumaara")
	private int lkm;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tilaus getTilaus() {
		return tilaus;
	}

	public void setTilaus(Tilaus tilaus) {
		this.tilaus = tilaus;
	}

	public Tuote getTuote() {
		return tuote;
	}

	public void setTuote(Tuote tuote) {
		this.tuote = tuote;
	}

	public int getLkm() {
		return lkm;
	}

	public void setLkm(int lkm) {
		this.lkm = lkm;
	}

}
