package com.ro8.varastosofta.application.model;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 * Apuluokka "Tooltippien" asettamiseen eri komponentteihin.
 * 
 * @author Riina Antikainen, Tuukka Mytty, Janne Valle.
 * 
 *
 */
public class Tooltipit {
	
	public static void asetaTooltip(TextField textfield, String viesti) {
		
		Tooltip tooltip = new Tooltip(viesti);
		
		textfield.setTooltip(tooltip);
		
	}
	
	public static void asetaTooltip(Button button, String viesti) {
		
		Tooltip tooltip = new Tooltip(viesti);
		
		button.setTooltip(tooltip);
	}

}
