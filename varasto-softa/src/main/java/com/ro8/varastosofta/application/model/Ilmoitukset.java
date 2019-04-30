package com.ro8.varastosofta.application.model;

import java.util.Optional;

import com.ro8.varastosofta.application.Lokaali;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Luokka ilmoituksille jotka kertovat kun käyttäjä on saanut tehtävänsa suoritettua.
 * 
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 *
 */
public class Ilmoitukset {
	
	/**
	 * Ilmoitus käyttäjän onnistuneesta lisäämisestä tietokantaan.
	 */
	public static void kayttajaLisattyOnnistuneestiIlmo() {
		
		informaatioAlertti("Information Dialog", null, Lokaali.getBundle().getString("alert.user.succesfulAdd"));
		
	}
	
	/**
	 * Ilmoitus jos käyttäjän lisääminen tietokantaan ei onnistunut.
	 */
	public static void kayttajaLisaysEiOnnistunutIlmo() {
		
		informaatioAlertti("Information Dialog", null, Lokaali.getBundle().getString("alert.user.failedAdd"));
		
	}
	
	/**
	 * Varmistetaan käyttäjän poisto.
	 * 
	 * @return true, jos käyttäjä halutaan poistaa ja false muuten.
	 */
	public static boolean kayttajanPoistonVarmistus() {
		
		return confirmaatioAlertti("Confirmation Dialog", null, Lokaali.getBundle().getString("alert.user.deleteUser"));
		
	}
	
	/**
	 * Ilmoitus käyttäjän onnistuneesta tietokannasta poistosta.
	 */
	public static void kayttajaPoistettuOnnistuneesti() {
		
		informaatioAlertti("Information Dialog", null, Lokaali.getBundle().getString("alert.user.succesfulRemove"));
		
	}
	
	/**
	 * Ilmoitus jos käyttäjää ei poistettu tietokannasta.
	 */
	public static void kayttajaPoistettuEiOnnistunut() {
			
		informaatioAlertti("Information Dialog", null, Lokaali.getBundle().getString("alert.user.failedRemove"));
		
	}
	
	/**
	 * Ilmoitus tuotteen onnistuneesta lisäämisestä tietokantaan.
	 */
	public static void tuoteLisattyOnnistuneestiIlmo() {
			
		informaatioAlertti("Information Dialog", null, Lokaali.getBundle().getString("alert.product.succesfulAdd"));
		
	}
	
	/**
	 * Ilmoitus jos tuotteen lisääminen tietokantaan ei onnistunut.
	 */
	public static void tuotteenLisaysEiOnnistunutIlmo() {
		
		informaatioAlertti("Information Dialog", null, Lokaali.getBundle().getString("alert.product.failedAdd"));
		
	}
	
	/**
	 * Ilmoitus tuotteen onnistuneesta tietokannasta poistosta.
	 */
	public static void tuotePoistettuOnnistuneesti() {
		
		informaatioAlertti("Information Dialog", null, Lokaali.getBundle().getString("alert.product.succesfulRemove"));
		
	}
	
	/**
	 * Ilmoitus jos tuotetta ei poistettu tietokannasta.
	 */
	public static void tuotePoistettuEiOnnistunut() {
			
		informaatioAlertti("Information Dialog", null, Lokaali.getBundle().getString("alert.product.failedRemove"));
		
	}
	
	/**
	 * Varmistetaan tuotteen poisto.
	 * 
	 * @return true, jos tuote halutaan poistaa ja false muuten.
	 */
	public static boolean tuotteenPoistonVarmistus() {
		
		return confirmaatioAlertti("Confirmation Dialog", null, Lokaali.getBundle().getString("alert.product.deleteProduct"));
		
	}
	
	/**
	 * Varmistetaan uloskirjautuminen
	 * 
	 * @return true, jos kirjaudutaan ulos ja false muuten
	 */
	public static boolean uloskirjautumisenVarmistus() {
		
		return confirmaatioAlertti("Confirmation Dialog", null, Lokaali.getBundle().getString("alert.logout"));
			
	}
	
	/**
	 * Ohjelman lopettamisen varmistus.
	 * 
	 * @return true, jos lopetus varmistettu ja false muuten.
	 */
	public static boolean ohjelmanLopetusVarmistus() {
		
		return confirmaatioAlertti("Confirmation Dialog", null, Lokaali.getBundle().getString("alert.exit"));
			
	}
	
	/**
	 * Tuotteen lisäyksen varmistus.
	 * 
	 * @return true, jos tuote halutaan lisätä ja false muuten.
	 */
	public static boolean tuotteenLisaysVarmistus() {
		
		return confirmaatioAlertti("Confirmation Dialog", null, Lokaali.getBundle().getString("alert.product.addProduct"));
	}
	
	/**
	 * Tuottaa toiminnon varmistamis/hylkäys dialogin.
	 * 
	 * @param titteli, dialogin titteli.
	 * @param headeri, dialogin otsake.
	 * @param contentti, dialogin kuvaava sisältöteksti.
	 * @return
	 */
	private static boolean confirmaatioAlertti(String titteli, String headeri, String contentti) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(titteli);
		alert.setHeaderText(headeri);
		alert.setContentText(contentti);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.OK){
		    
			return true;
			
		} else {
			return false;	
		}
		
		
	}
	
	/**
	 * Tuottaa ilmoituksen siitä kun käyttäjän suorittama toiminto on saatu loppuun.
	 * 
	 * @param titteli, dialogin titteli.
	 * @param headeri, dialogin otsake.
	 * @param contentti, dialogin kuvaava sisältö.
	 */
	private static void informaatioAlertti(String titteli, String headeri, String contentti) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titteli);
		alert.setHeaderText(headeri);
		alert.setContentText(contentti);

		alert.showAndWait();
		
	}
	
	

}


































