package com.ro8.varastosofta.application.model;

import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;

/**
 * Apuluokka "Tooltippien" asettamiseen eri komponentteihin.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class Tooltipit {
	
	/**
	 * Asettaa "Tooltipin" komponenttiin ja siihen tekstin.
	 * @param control, JavaFx komponentti johon Tooltip asetetaan.
	 * @param viesti, viesti joka lisätään Tooltippiin.
	 */
	public static void asetaTooltip(Control control, String viesti) {
		Tooltip tooltip = new Tooltip(viesti);
		control.setTooltip(tooltip);
	}

}
