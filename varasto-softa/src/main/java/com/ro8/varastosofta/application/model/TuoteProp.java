package com.ro8.varastosofta.application.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TuoteProp {
	
	private SimpleIntegerProperty id;
	private SimpleStringProperty nimi;
	private SimpleIntegerProperty lkm;
	private SimpleStringProperty tuoteryhma;
	
	public TuoteProp(int id, String nimi, int lkm, String tuoteryhma) {
		this.id = new SimpleIntegerProperty(id);
		this.nimi = new SimpleStringProperty(nimi);
		this.lkm = new SimpleIntegerProperty(lkm);
		this.tuoteryhma = new SimpleStringProperty(tuoteryhma);
	}
	
	/**
	 * get-metodi tuoteryhmälle
	 */
	public String getTuoteryhma() {
		return this.tuoteryhma.get();
	}

	/**
	 * set-metodi tuoteryhmälle
	 * @param tuoteryhmä tuotteen tuoteryhmä
	 */
	public void setTuoteryhma(String tuoteryhma) {
		this.tuoteryhma = new SimpleStringProperty(tuoteryhma);
	}

	/**
	 * set-metodi id:lle
	 * @param id tuotteen id
	 */
	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}
	
	/**
	 * get-metodi id:lle
	 */
	public int getId() {
		return this.id.get();
	}
	
	/**
	 * set-metodi tuotteen nimelle
	 * @param nimi tuotteen nimi
	 */
	public void setNimi(String nimi) {
		this.nimi = new SimpleStringProperty(nimi);
	}
	
	/**
	 * get-metodi nimelle
	 */
	public String getNimi() {
		return this.nimi.get();
	}
	
	/**
	 * set-metodi tuotteen lukumäärälle
	 * @param lkm tuotteen lukumäärä
	 */
	public void setLkm(int lkm) {
		this.lkm = new SimpleIntegerProperty(lkm);
	}
	
	/**
	 * get-metodi tuotteen lukumäärälle
	 */
	public int getLkm() {
		return this.lkm.get();
	}
	
	@Override
	public String toString() {
		return this.getId() + " " + this.getNimi() + " " + this.getLkm() + " kpl";
	}
}





















