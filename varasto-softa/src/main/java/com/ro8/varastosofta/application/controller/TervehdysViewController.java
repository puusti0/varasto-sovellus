package com.ro8.varastosofta.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Tervetuloasivun hallinnointi.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle.
 */
public class TervehdysViewController extends Controller {
	
	@FXML
	private Label welcomeLabel;
	
	/**
	 * Lisätään vihjeet komponentteihin.
	 */
	@Override
	public void lisaaVihjeetKomponentteihin() {
		return;
	}
	
	public void init() {
		this.welcomeLabel.setText(this.getKaannokset().kaanna("welcome.hello"));
	}
	
}
