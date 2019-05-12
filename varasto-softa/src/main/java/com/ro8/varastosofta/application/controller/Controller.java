package com.ro8.varastosofta.application.controller;

import java.util.ResourceBundle;

import com.ro8.varastosofta.application.Kaannokset;
import com.ro8.varastosofta.application.model.Ilmoitukset;
import com.ro8.varastosofta.application.model.Tooltipit;
import com.ro8.varastosofta.application.model.Validaattori;


/**
 * void init();
	void setKaannokset(ResourceBundle kaannokset);
	
	void initSession(Istunto sessionManager, String sessionID);
	void setViewMenu(Menu menu);
 */

/**
 * Näkymien kontrolleri pohja, joka sisältää kaikki tarvittavat apuluokkien luomiset.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle.
 */
public abstract class Controller {
	
	private Ilmoitukset ilmoitukset;
	private Tooltipit vihjeet;
	private Validaattori validaattori;
	private Kaannokset kaannokset;
	
	/**
	 * Controller konstruktori.
	 */
	public Controller() {
		this.ilmoitukset = new Ilmoitukset();
		this.vihjeet = new Tooltipit();
		this.validaattori = new Validaattori();
		this.kaannokset = Kaannokset.getInstance();
	}
	
	/**
	 * Lisätään vihjeet komponentteihin.
	 */
	abstract void lisaaVihjeetKomponentteihin();

	/**
	 * Palautetaan ilmoitukset.
	 * @return ilmoitukset
	 */
	public Ilmoitukset getIlmoitukset() {
		return ilmoitukset;
	}
	
	/**
	 * Palautetaan vihjeet.
	 * @return vihjeet
	 */
	public Tooltipit getVihjeet() {
		return vihjeet;
	}
	
	/**
	 * Palautetaan validaattori.
	 * @return
	 */
	public Validaattori getValidaattori() {
		return validaattori;
	}

	/**
	 * Palautetaan käännökset, joka vastaa tekstien lokalisoinnista.
	 * @return käännökset
	 */
	public Kaannokset getKaannokset() {
		return kaannokset;
	}

}
