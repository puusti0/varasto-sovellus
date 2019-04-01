package com.ro8.varastosofta.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tuote tietokantataulu.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
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
		
		/**
		 * Luokan Tuote konstruktori
		 * @param tuoteid uuden tuotteen id
		 * @param nimi uuden tuotteen nimi
		 * @param lukumaara uuden tuotteen varastosaldo
		 */
		public Tuote(int tuoteid, String nimi, int lukumaara) {
			this.id = tuoteid;
			this.nimi = nimi;
			this.lkm = lukumaara;
		}
		
		/**
		 * Luokan Tuote konstruktori
		 * @param tuoteid uuden tuotteen id
		 * @param nimi uuden tuotteen nimi
		 * @param lukumaara uuden tuotteen varastosaldo
		 * @param tr uuden tuotteen tuoteryhmä
		 */
		public Tuote(int tuoteid, String nimi, int lukumaara, Tuoteryhma tr) {
			this.id = tuoteid;
			this.nimi = nimi;
			this.lkm = lukumaara;
			this.tuoteryhma = tr;
		}

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
		 * get-metodi tuotteen tuoteryhmälle
		 */
		public Tuoteryhma getTuoteryhma() {
			return tuoteryhma;
		}

		/**
		 * set-metodi tuotteen tuoteryhmälle
		 * @param tuoteryhma tuotteen tuoterymä
		 */
		public void setTuoteryhma(Tuoteryhma tuoteryhma) {
			this.tuoteryhma = tuoteryhma;
		}

		/**
		 * get-metodi tuotteen nimelle
		 */
		public String getNimi() {
			return nimi;
		}

		/**
		 * set-metodi tuotteen nimelle
		 * @param nimi tuotteen nimi
		 */
		public void setNimi(String nimi) {
			this.nimi = nimi;
		}

		/**
		 * get-metodi tuotteen lukumäärälle
		 */
		public int getLkm() {
			return lkm;
		}

		/**
		 * set-metodi tuotteen lukumäärälle
		 * @param lkm tuotteen lukumäärä
		 */
		public void setLkm(int lkm) {
			this.lkm = lkm;
		}

		/**
		 * get-metodi tuotteen hinnalle
		 */
		public double getHinta() {
			return hinta;
		}

		/**
		 * set-metodi tuotteen hinnalle
		 * @param hinta tuotteen hinta
		 */
		public void setHinta(double hinta) {
			this.hinta = hinta;
		}

}
