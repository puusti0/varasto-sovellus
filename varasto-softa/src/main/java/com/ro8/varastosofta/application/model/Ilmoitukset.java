package com.ro8.varastosofta.application.model;

import java.util.Optional;

import com.ro8.varastosofta.application.Kaannokset;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Luokka ilmoituksille jotka kertovat kun käyttäjä on saanut tehtävänsa suoritettua.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class Ilmoitukset {
	
	private Kaannokset kaannokset;
	
	public Ilmoitukset() {
		this.kaannokset = Kaannokset.getInstance();
	}
	
	/**
	 * Tuottaa toiminnon varmistamis/hylkäys dialogin.
	 * @param headeri, dialogin otsake.
	 * @param contentti, dialogin kuvaava sisältöteksti.
	 * @return
	 */
	public boolean confirmaatioAlertti(String headeri, String sisalto) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(this.kaannokset.kaanna("alert.confirmationDialog"));
		alert.setHeaderText(headeri);
		alert.setContentText(this.kaannokset.kaanna(sisalto));
		Optional<ButtonType> result = alert.showAndWait();
		return result.isPresent() && result.get() == ButtonType.OK;
	}
	
	/**
	 * Tuottaa ilmoituksen siitä kun käyttäjän suorittama toiminto on saatu loppuun.
	 * @param headeri, dialogin otsake.
	 * @param contentti, dialogin kuvaava sisältö.
	 */
	public void informaatioAlertti(String headeri, String sisalto) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(this.kaannokset.kaanna("alert.informationDialog"));
		alert.setHeaderText(headeri);
		alert.setContentText(this.kaannokset.kaanna(sisalto));
		alert.showAndWait();
	}
	
	/**
	 * Tuottaa ilmoituksen siitä kun käyttäjän suorittama toiminto on saatu loppuun.
	 * @param headeri, dialogin otsake.
	 * @param contentti, dialogin kuvaava sisältö.
	 */
	public void errorAlertti(String headeri, String sisalto) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(this.kaannokset.kaanna("alert.user.failedLogon.tittle"));
		alert.setHeaderText(headeri);
		alert.setContentText(this.kaannokset.kaanna(sisalto));
		alert.showAndWait();
	}
}


































