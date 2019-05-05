package com.ro8.varastosofta.application.controller;

import java.io.IOException;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.ro8.varastosofta.application.Paaohjelma;

/**
 * Testiluokka kirjautumissivulle.
 * @author Riina Antikainen
 */
class KirjauduViewControllerTest extends ApplicationTest {
	
	private Parent kirjauduNode;
	
	/**
	 * Kirjautumissivun lataus testausta varten.
	 * @param stage - 
	 * @throws IOException 
	 */
	@Override
	public void start(Stage stage) throws IOException {
		kirjauduNode = FXMLLoader.load(Paaohjelma.class.getResource("view/Kirjaudu.fxml"));
		stage.setScene(new Scene(kirjauduNode));
		stage.show();
		stage.toFront();
	}
	
	/**
	 * Testataan käynnistymisessä alustetut arvot.
	 */
	// @Test
	public void testaaAlkuarvot() {
		FxAssert.verifyThat("#tunnusLabel", LabeledMatchers.hasText("Käyttäjätunnus:"));
		FxAssert.verifyThat("#tunnusTextField", TextInputControlMatchers.hasText(""));
		FxAssert.verifyThat("#salasanaLabel", LabeledMatchers.hasText("Salasana:"));
		FxAssert.verifyThat("#salasanaTextField", TextInputControlMatchers.hasText(""));
		FxAssert.verifyThat("#kirjauduButton", LabeledMatchers.hasText("Kirjaudu"));
		FxAssert.verifyThat("#tyhjennaButton", LabeledMatchers.hasText("Tyhjennä"));
		FxAssert.verifyThat("#lopetaButton", LabeledMatchers.hasText("Lopeta"));
	}
	
	/**
	 * Testataan "Tyhjennä" napin toiminnalisuus.
	 */
	// @Test
	public void testaaTyhjennaButton() {
		rightClickOn("#tyhjennaButton");
		FxAssert.verifyThat("#tunnusTextField", TextInputControlMatchers.hasText(""));
		FxAssert.verifyThat("#salasanaTextField", TextInputControlMatchers.hasText(""));
	}

}
