package com.ro8.varastosofta.application.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tilaus")
public class Tilaus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name ="myyntiedustaja_id")
	private Myyntiedustaja myyntiedustaja;
	
	@Column(name ="varastotyontekija_id")
	private Varastotyontekija varastotyontekija;
	
	@Column(name ="asiakasyritys_id")
	private Asiakasyritys asiakasyritys;
	
	@Column(name ="aikaleima")
	private Date aikaleima;
	
	@Column(name ="loppusumma")
	private double loppusumma;

	/**
	 * get-metodi Id:lle
	 */
	public int getId() {
		return id;
	}

	/**
	 * set-metodi Id:lle
	 * @param id asetettava id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get-metodi tilauksen myyntiedustajalle
	 */
	public Myyntiedustaja getMyyntiedustaja() {
		return myyntiedustaja;
	}

	/**
	 * set-metodi tilauksen myyntiedustajalle
	 * @param myyntiedustaja tilauksen myyntiedustaja
	 */
	public void setMyyntiedustaja(Myyntiedustaja myyntiedustaja) {
		this.myyntiedustaja = myyntiedustaja;
	}

	/**
	 * get-metodi varastotyöntekijälle
	 */
	public Varastotyontekija getVarastotyontekija() {
		return varastotyontekija;
	}

	/**
	 * set-metodi varastotyöntekijälle
	 * @param varastotyöntekijä tilauksen varastotyöntekijä
	 */
	public void setVarastotyontekija(Varastotyontekija varastotyontekija) {
		this.varastotyontekija = varastotyontekija;
	}

	/**
	 * get-metodi asiakasyritykselle
	 */
	public Asiakasyritys getAsiakasyritys() {
		return asiakasyritys;
	}

	/**
	 * set-metodi asiakasyritykselle
	 * @param asiakasyritys tilauksen asiakasyritys
	 */
	public void setAsiakasyritys(Asiakasyritys asiakasyritys) {
		this.asiakasyritys = asiakasyritys;
	}

	/**
	 * get-metodi tilauksen aikaleimalle
	 */
	public Date getAikaleima() {
		return aikaleima;
	}

	/**
	 * set-metodi aikaleimalle
	 * @param aikaleima tilauksen aikaleima
	 */
	public void setAikaleima(Date aikaleima) {
		this.aikaleima = aikaleima;
	}

	/**
	 * get-metodi tilauksen loppusummalle
	 */
	public double getLoppusumma() {
		return loppusumma;
	}

	/**
	 * set-metodi tilauksen loppusummalle
	 * @param loppusumma tilauksen loppusumma
	 */
	public void setLoppusumma(double loppusumma) {
		this.loppusumma = loppusumma;
	}
	
}
