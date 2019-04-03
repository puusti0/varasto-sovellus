package com.ro8.varastosofta.application.model;

import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;

/**
 * Apuluokka "Tooltippien" asettamiseen eri komponentteihin.
 * 
 * @author Riina Antikainen, Tuukka Mytty, Janne Valle.
 * 
 *
 */
public class Tooltipit {
	
	public static void asetaTooltip(Control control, String viesti) {
		
		Tooltip tooltip = new Tooltip(viesti);
		
		control.setTooltip(tooltip);
		
	}
	
	

}
