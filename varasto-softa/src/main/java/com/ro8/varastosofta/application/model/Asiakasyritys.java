package com.ro8.varastosofta.application.model;

import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Asiakasyritys")
public class Asiakasyritys {
	
	@Id
	@Column(name ="id")
	private int id;
	
	@Column(name ="asiakasyritys_id")
	private Asiakasyritys asiakasyritys;

	/**
	 * get-metodi Id:lle
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * set-metodi Id:lle
	 * @param id uusi id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * get-metodi asiakasyritykselle
	 */
	public Asiakasyritys getAsiakasyritys() {
		return asiakasyritys;
	}

	/**
	 * set-metodi asiakasyritykselle
	 * @param asiakasyritys uusi asiakasyritys
	 */
	public void setAsiakasyritys(Asiakasyritys asiakasyritys) {
		this.asiakasyritys = asiakasyritys;
	}

}