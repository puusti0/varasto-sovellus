package com.ro8.varastosofta.application.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 * Testiluokka Tooltipit-luokan testaamiseen.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
class TooltipitTest {
	
	/**
	 * Testi sille asettaako testattava metodi Tooltipin niin kuin kuuluu.
	 */
	@Disabled
	@Test
	void testAsetaTooltip() {
		TextField textField = new TextField();
		TextField textFieldTyhja = new TextField();
		Button button = new Button();
		Button buttonTyhja = new Button();
		String teksti = "testi";
		Tooltipit tooltipit = new Tooltipit();
		
		tooltipit.aseta(textField, teksti);
		tooltipit.aseta(button, teksti);
		
		assertTrue(textField.getTooltip() instanceof Tooltip, "Oikein meni, TextField");
		assertFalse(textFieldTyhja.getTooltip() instanceof Tooltip, "Ei tooltippia, TextField");
		
		assertTrue(button.getTooltip() instanceof Tooltip, "Oikein meni, Button");
		assertFalse(buttonTyhja.getTooltip() instanceof Tooltip, "Ei tooltippia, Button");
	}

}
