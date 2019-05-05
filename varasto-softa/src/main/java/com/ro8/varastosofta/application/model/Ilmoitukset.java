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
	 * Tuottaa toiminnon varmistamis/hylkäys dialogin.
	 * 
	 * @param titteli, dialogin titteli.
	 * @param headeri, dialogin otsake.
	 * @param contentti, dialogin kuvaava sisältöteksti.
	 * @return
	 */
	public boolean confirmaatioAlertti(String titteli, String headeri, String contentti) {
		
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
	public void informaatioAlertti(String titteli, String headeri, String contentti) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titteli);
		alert.setHeaderText(headeri);
		alert.setContentText(contentti);

		alert.showAndWait();
		
	}
	
	

}


































