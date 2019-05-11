package com.ro8.varastosofta.application.controller;

import com.ro8.varastosofta.application.model.Ilmoitukset;
import com.ro8.varastosofta.application.model.Tooltipit;

/**
 * Näkymien kontrolleri pohja, joka sisältää kaikki tarvittavat apuluokkien luomiset.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle.
 */
public abstract class Controller {
	
	private Ilmoitukset ilmoitukset;
	private Tooltipit vihjeet;
	
	/**
	 * Controller konstruktori.
	 */
	public Controller() {
		this.ilmoitukset = new Ilmoitukset();
		this.vihjeet = new Tooltipit();
	}

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

}
