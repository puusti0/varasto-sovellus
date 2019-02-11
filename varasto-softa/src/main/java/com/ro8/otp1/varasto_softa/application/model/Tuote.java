package com.ro8.otp1.varasto_softa.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tuote")
public class Tuote {
		
		@Id
		@Column(name ="TuoteId")
		private int id;
		
		@Column(name ="Nimi")
		private String nimi;
		
		@Column(name ="Lukumaara")
		private int lkm;
		
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

		public Tuote() {
			
		}

}
