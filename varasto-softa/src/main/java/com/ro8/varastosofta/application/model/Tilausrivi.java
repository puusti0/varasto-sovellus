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

	/**
	 *get-metodi tilausrivin id:lle
	 */
	public int getId() {
		return id;
	}

	/**
	 * set-metodi tilausrivin id:lle
	 * @param id tilausrivin id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 *get-metodi tilaukselle
	 */
	public Tilaus getTilaus() {
		return tilaus;
	}

	/**
	 * set-metodi tilausrivin tilaukselle
	 * @param tilaus tilausrivin tilaus
	 */
	public void setTilaus(Tilaus tilaus) {
		this.tilaus = tilaus;
	}

	/**
	 *get-metodi tilausrivin tuotteelle
	 */
	public Tuote getTuote() {
		return tuote;
	}

	/**
	 * set-metodi tilausrivin tuotteelle
	 * @param tuote tilausrivin tuote
	 */
	public void setTuote(Tuote tuote) {
		this.tuote = tuote;
	}

	/**
	 *get-metodi tilausrivin tuotteiden lukumäärälle
	 */
	public int getLkm() {
		return lkm;
	}

	/**
	 * set-metodi tilausrivin tuotteiden lukumäärälle
	 * @param lkm tilausrivin tuotteiden lukumäärä
	 */
	public void setLkm(int lkm) {
		this.lkm = lkm;
	}

}
