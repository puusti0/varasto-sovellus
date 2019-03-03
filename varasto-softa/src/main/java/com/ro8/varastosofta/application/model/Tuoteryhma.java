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
	
	@OneToMany(mappedBy= "tuoteryhma")
	private List<Tuote> tuotteet;

	public Tuoteryhma() {
		
	}

	public List<Tuote> getTuotteet() {
		return tuotteet;
	}

	public void setTuotteet(List<Tuote> tuotteet) {
		this.tuotteet = tuotteet;
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
	
}
