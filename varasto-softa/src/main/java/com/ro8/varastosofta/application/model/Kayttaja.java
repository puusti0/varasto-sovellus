package com.ro8.varastosofta.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Kayttaja")
public class Kayttaja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	/**@ManyToOne
	@JoinColumn(name="rooli_Id")
	private Rooli rooli;*/
	
	@Column(name ="kayttajatunnus")
	private String kayttajatunnus;
	
	@Column(name ="salasana")
	private String salasana;
}
