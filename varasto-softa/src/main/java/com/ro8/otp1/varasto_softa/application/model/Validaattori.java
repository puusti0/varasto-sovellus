package com.ro8.otp1.varasto_softa.application.model;


/**
 * Luokka syötteiden validointia varten.
 *
 */
public class Validaattori {
	
	
	/**
	 * Testaa onko parametreina annetut merkkijonot valideja eli eivät ole tyhjia,
	 * id ja lkm ovat numeroita ja nimi ei saa olla yli 20 merkkiä pitkä. Palauttaa
	 * true jos parametrit valideja muuten false.
	 * 
	 * @param id
	 * @param nimi
	 * @param lkm
	 * @return
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
	 * Testaa onko poistettavan tuottenn id validi.
	 * @param id
	 * @return
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
	 * @param strNum
	 * @return
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

}
