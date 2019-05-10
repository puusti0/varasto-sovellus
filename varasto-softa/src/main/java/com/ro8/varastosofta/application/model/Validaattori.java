package com.ro8.varastosofta.application.model;

/**
 * Luokka syötteiden validointia varten.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class Validaattori {
	
	/**
	 * Testaa onko parametreina annetut merkkijonot valideja eli eivät ole tyhjia,
	 * id ja lkm ovat numeroita ja nimi ei saa olla yli 20 merkkiä pitkä. Palauttaa
	 * true jos parametrit valideja muuten false.
	 * @param id tuotteen id
	 * @param nimi tuotteen nimi
	 * @param lkm tuotteen lukumäärä
	 * @return true jos tuotteen tiedot oikean muotoiset ja false muuten.
	 */
	public boolean onkoLisattavaTuoteValidi(String id, String nimi, String lkm) {
		if(id.length() == 0 || nimi.length() == 0 || lkm.length() == 0) {
			return false;
		} 
		if(!onkoNumero(id) || !onkoNumero(lkm)) {
			return false;
		}
		return nimi.length() <= 20;
	}
	
	
	/**
	 * Testaa onko poistettavan tuotteen id validi.
	 * @param id tuotteen id.
	 * @return palauttaa true jos tuotteen id on validi ja false muuten.
	 */
	public boolean onkoPoistettavaIdValidi(String id) {
		String regex = "[0-9]+";
		return id.matches(regex);
	}
	
	/**
	 * Testaa onko parametri muunnettavissa numeroksi. Palauttaa true jos on
	 * muuten false.
	 * @param strNum numero String muodossa-
	 * @return true jos on numero ja false muuten.
	 */
	public boolean onkoNumero(String numero) {
		String regex = "[0-9]+";
		return numero.matches(regex);
	}
	
	/**
	 * Testaa onko annettu tuoteryhmä validi.
	 * @param tuoteryhma, annettu tuoteryhmä String muodossa.
	 * @return, false jos tuoteryhmä ei validi, true muuten.
	 */
	public boolean onkoTuoteryhmaValidi(String tuoteryhma) {
		return tuoteryhma.length() != 0 && tuoteryhma.length() <= 20;
	}
	
	/**
	 * Tarkistetaan, että lisättävän käyttäjän käyttäjätunnus ja salasana ei sisällä ylimääräisiä tietoja.
	 * @param tunnus käyttäjän käyttäjätunnus
	 * @param salasana käyttäjän salasana
	 * @return true jos tuotteen tiedot oikean muotoiset ja false muuten.
	 */
	public boolean onkoLisattavaKayttajaValidi(String tunnus, String salasana) {
		// Tunnuksen tarkastaminen
		if(tunnus.contains(";") || tunnus.length() == 0) {
			return false;
		} 
		// Salasanan tarkastaminen
		return !salasana.contains(";");
	}
	
}





















