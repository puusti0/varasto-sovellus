package com.ro8.varastosofta.application;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Kielivalinnan hallinta ja tekstien lokalisointi.
 * Suunnittelumalli: Singleton
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class Kaannokset {

	private final static Kaannokset KAANNOKSET = new Kaannokset();
	private Locale kieli;
	
	/**
	 * Kaannokset konstruktori.
	 * Alustetaan default kieleksi englanti.
	 */
	private Kaannokset() {
		this.kieli = new Locale("en","GB");
	}
	
	/**
	 * Palautetaan Käännökset ilmentymä.
	 * @return Käännökset ilmentymä
	 */
	public static Kaannokset getInstance() {
		return KAANNOKSET;
	}

	/**
	 * Asetetaan käytettävä kieli
	 * @param kieli asennettava kieli
	 */
	public void setKieli(Locale kieli) {
		this.kieli = kieli;
	}

	/**
	 * Palautetaan käytössä oleva kieli.
	 * @return valittu kieli
	 */
	public Locale getKieli() {
		return kieli;
	}

	/**
	 * Palautetaan käytössä oleva kielitiedosto.
	 * @return kielitiedosto
	 */
	public ResourceBundle haeKielitiedosto() {
		return ResourceBundle.getBundle("MessagesBundle", getKieli(), new UTF8Control());
	}
	
	/**
	 * Käännä sana käytössä olevalle kielelle.
	 * @param kaannettava sana tai lause, joka käännetään
	 * @return käännetty sana tai lause
	 */
	public String kaanna(String kaannettava) {
		String kaannos = "";
		kaannos = haeKielitiedosto().getString(kaannettava);
		return kaannos;
	}
	
}
