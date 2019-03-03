package com.ro8.varastosofta.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Tuote")
public class Tuote {
		
		@Id
		@Column(name ="id")
		private int id;
		
		@ManyToOne
		@JoinColumn(name="tuoteryhma_Id")
		private Tuoteryhma tuoteryhma;
		
		@Column(name ="nimi")
		private String nimi;
		
		@Column(name ="lukumaara")
		private int lkm;
		
		@Column(name ="hinta")
		private double hinta;
		
		public Tuote() {
			
		}
		
		public Tuote(int tuoteid, String nimi, int lukumaara) {
			this.id = tuoteid;
			this.nimi = nimi;
			this.lkm = lukumaara;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Tuoteryhma getTuoteryhma() {
			return tuoteryhma;
		}

		public void setTuoteryhma(Tuoteryhma tuoteryhma) {
			this.tuoteryhma = tuoteryhma;
		}

		public String getNimi() {
			return nimi;
		}

		public void setNimi(String nimi) {
			this.nimi = nimi;
		}

		public int getLkm() {
			return lkm;
		}

		public void setLkm(int lkm) {
			this.lkm = lkm;
		}

		public double getHinta() {
			return hinta;
		}

		public void setHinta(double hinta) {
			this.hinta = hinta;
		}

}
