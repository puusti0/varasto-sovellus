package com.ro8.varastosofta.application.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 * Testiluokka Tooltipit-luokan testaamiseen.
 * 
 * @author Riina Antikainen, Tuukka Mytty, Janne Valle
 *
 */
class TooltipitTest {
	
	/**
	 * Testi sille asettaako testattava metodi Tooltipin niin kuin kuuluu.
	 */
	@Disabled
	@Test
	void testAsetaTooltip() {
		
		JFXPanel fxPanel = new JFXPanel();
		TextField textField = new TextField();
		TextField textFieldTyhja = new TextField();
		Button button = new Button();
		Button buttonTyhja = new Button();
		String teksti = "testi";
		Tooltipit tooltipit = new Tooltipit();
		
		tooltipit.asetaTooltip(textField, teksti);
		tooltipit.asetaTooltip(button, teksti);
		
		assertTrue(textField.getTooltip() instanceof Tooltip, "Oikein meni, TextField");
		assertFalse(textFieldTyhja.getTooltip() instanceof Tooltip, "Ei tooltippia, TextField");
		
		assertTrue(button.getTooltip() instanceof Tooltip, "Oikein meni, Button");
		assertFalse(buttonTyhja.getTooltip() instanceof Tooltip, "Ei tooltippia, Button");
	}

}
