package com.ro8.varastosofta.application.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TuoteProp {
	
	private SimpleIntegerProperty id;
	private SimpleStringProperty nimi;
	private SimpleIntegerProperty lkm;
	
	public TuoteProp(int id, String nimi, int lkm) {
		this.id = new SimpleIntegerProperty(id);
		this.nimi = new SimpleStringProperty(nimi);
		this.lkm = new SimpleIntegerProperty(lkm);
	}
	
	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}
	public int getId() {
		return this.id.get();
	}
	public void setNimi(String nimi) {
		this.nimi = new SimpleStringProperty(nimi);
	}
	public String getNimi() {
		return this.nimi.get();
	}
	public void setLkm(int lkm) {
		this.lkm = new SimpleIntegerProperty(lkm);
	}
	public int getLkm() {
		return this.lkm.get();
	}
	

}





















