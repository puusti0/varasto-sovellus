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
	 * 
	 * @param id tuotteen id
	 * @param nimi tuotteen nimi
	 * @param lkm tuotteen lukumäärä
	 * @return true jos tuotteen tiedot oikean muotoiset ja false muuten.
	 */
	public static boolean onkoLisattavaTuoteValidi(String id, String nimi, String lkm) {
		
		boolean result = true;
		
		if(id.length() == 0 || nimi.length() == 0 || lkm.length() == 0) {
			result = false;
		} 
		
		if(!onkoNumero(id) || !onkoNumero(lkm)) {
			result = false;
		}
		
		if(nimi.length() > 20) {
			result = false;
		}
		
		return result;
	}
	
	
	/**
	 * Testaa onko poistettavan tuotteen id validi.
	 * @param id tuotteen id.
	 * @return palauttaa true jos tuotteen id on validi ja false muuten.
	 */
	public static boolean onkoPoistettavaIdValidi(String id) {
		
		boolean result = true;
		
		if(id.length() == 0) {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * Testaa onko parametri muunnettavissa numeroksi. Palauttaa true jos on
	 * muuten false.
	 * 
	 * @param strNum numero String muodossa-
	 * @return true jos on numero ja false muuten.
	 */
	public static boolean onkoNumero(String strNum) {
		
		boolean result = true;
	    
		try {
			
	        int i = Integer.parseInt(strNum);
	        
	    } catch (NumberFormatException nfe) {
	    	
	    	result = false;
	    	
	        return result;
	    }
		
	    return result;
	}
	
	/**
	 * Testaa onko annettu tuoteryhmä validi.
	 * 
	 * @param tuoteryhma, annettu tuoteryhmä String muodossa.
	 * @return, false jos tuoteryhmä ei validi, true muuten.
	 */
	public static boolean onkoTuoteryhmaValidi(String tuoteryhma) {
		
		boolean result = true;
		
		if(tuoteryhma.length() == 0 || tuoteryhma.length() > 20) {
			return result = false;
		}
		
		return result;
		
	}
	
	/**
	 * Tarkistetaan, että lisättävän käyttäjän käyttäjätunnus ja salasana ei sisällä ylimääräisiä tietoja.
	 * @param tunnus käyttäjän käyttäjätunnus
	 * @param salasana käyttäjän salasana
	 * @return true jos tuotteen tiedot oikean muotoiset ja false muuten.
	 */
	public static boolean onkoLisattavaKayttajaValidi(String tunnus, String salasana) {
		
		boolean result = true;
		
		// Tunnuksen tarkastaminen
		if(tunnus.contains(";") || tunnus.length() == 0) {
			result = false;
		} 
		
		// Salasanan tarkastaminen
		if(salasana.contains(";")) {
			result = false;
		}
		
		return result;
	}
	

}





















