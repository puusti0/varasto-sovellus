package com.ro8.varastosofta.application.model;

import java.util.Optional;

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
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("The user was successfully added into the database.");

		alert.showAndWait();
		
	}
	
	/**
	 * Ilmoitus jos käyttäjän lisääminen tietokantaan ei onnistunut.
	 */
	public static void kayttajaLisaysEiOnnistunutIlmo() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("The adding of the user into the database was not successful.");

		alert.showAndWait();
		
	}
	
	/**
	 * Varmistetaan käyttäjän poisto.
	 * 
	 * @return true, jos käyttäjä halutaan poistaa ja false muuten.
	 */
	public static boolean kayttajanPoistonVarmistus() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete the user?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    
			return true;
			
		} else {
		    
			return false;
			
		}
		
	}
	
	/**
	 * Ilmoitus käyttäjän onnistuneesta tietokannasta poistosta.
	 */
	public static void kayttajaPoistettuOnnistuneesti() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("The user was successfully removed from the database.");

		alert.showAndWait();
		
	}
	
	/**
	 * Ilmoitus jos käyttäjää ei poistettu tietokannasta.
	 */
	public static void kayttajaPoistettuEiOnnistunut() {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The user was not removed from the database.");
	
			alert.showAndWait();
			
		}
	
	

}

































