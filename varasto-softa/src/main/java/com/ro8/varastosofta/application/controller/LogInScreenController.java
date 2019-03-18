package com.ro8.varastosofta.application.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Kirjautumisruudulle kontrolleri.
 * 
 * @author Riina Antikainen, Tuukka Mytty, Janne Valle.
 *
 */
public class LogInScreenController {
	
	@FXML
	private TextField tunnusTextField;
	@FXML
	private TextField salasanaTextField;
	
	/**
	 * Tyhjennä napin painallus asettaa tunnus ja salasana kentät tyhjiksi.
	 */
	@FXML
	private void tyhjennaNappiaPainettu() {
		
		this.tunnusTextField.setText("");
		this.salasanaTextField.setText("");
		
		
	}
	
	/**
	 * Lopetus napin painallus sulkee ohjelman.
	 */
	@FXML
	private void lopetaNappiaPainettu() {
		
		Platform.exit();
		
	}
	
}
