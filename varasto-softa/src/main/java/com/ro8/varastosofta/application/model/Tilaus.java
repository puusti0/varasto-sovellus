package com.ro8.varastosofta.application.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tilaus")
public class Tilaus {
	
	@Id
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Myyntiedustaja getMyyntiedustaja() {
		return myyntiedustaja;
	}

	public void setMyyntiedustaja(Myyntiedustaja myyntiedustaja) {
		this.myyntiedustaja = myyntiedustaja;
	}

	public Varastotyontekija getVarastotyontekija() {
		return varastotyontekija;
	}

	public void setVarastotyontekija(Varastotyontekija varastotyontekija) {
		this.varastotyontekija = varastotyontekija;
	}

	public Asiakasyritys getAsiakasyritys() {
		return asiakasyritys;
	}

	public void setAsiakasyritys(Asiakasyritys asiakasyritys) {
		this.asiakasyritys = asiakasyritys;
	}

	public Date getAikaleima() {
		return aikaleima;
	}

	public void setAikaleima(Date aikaleima) {
		this.aikaleima = aikaleima;
	}

	public double getLoppusumma() {
		return loppusumma;
	}

	public void setLoppusumma(double loppusumma) {
		this.loppusumma = loppusumma;
	}
	
}
