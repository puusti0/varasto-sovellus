package com.ro8.varastosofta.application.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
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
		FXMLLoader loader = new FXMLLoader();
		loader.setController(new KirjauduController());
		loader.setResources(ResourceBundle.getBundle("MessagesBundle", new Locale("en","GB")));
		loader.setLocation(Paaohjelma.class.getResource("view/Kirjaudu.fxml"));
		kirjauduNode = (Parent)loader.load();
		stage.setScene(new Scene(kirjauduNode));
		stage.show();
		stage.toFront();
	}
	
	/**
	 * Testataan käynnistymisessä alustetut arvot.
	 */
	@Test
	public void testaaAlkuarvot() {
		FxAssert.verifyThat("#tunnusLabel", LabeledMatchers.hasText("Username"));
		FxAssert.verifyThat("#tunnusTextField", TextInputControlMatchers.hasText(""));
		FxAssert.verifyThat("#salasanaLabel", LabeledMatchers.hasText("Password"));
		FxAssert.verifyThat("#salasanaTextField", TextInputControlMatchers.hasText(""));
		FxAssert.verifyThat("#kirjauduButton", LabeledMatchers.hasText("Login"));
		FxAssert.verifyThat("#lopetaButton", LabeledMatchers.hasText("Quit"));
	}

}
