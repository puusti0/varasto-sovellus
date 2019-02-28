package com.ro8.varastosofta.application.model;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Asiakasyritys getAsiakasyritys() {
		return asiakasyritys;
	}

	public void setAsiakasyritys(Asiakasyritys asiakasyritys) {
		this.asiakasyritys = asiakasyritys;
	}

}
