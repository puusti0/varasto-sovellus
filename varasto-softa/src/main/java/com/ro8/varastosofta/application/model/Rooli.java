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
	
	/*@OneToMany(mappedBy= "kayttaja")
	private List<Kayttaja> kayttajat;*/
}
