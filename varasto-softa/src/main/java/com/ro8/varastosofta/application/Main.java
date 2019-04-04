package com.ro8.varastosofta.application;
	
import java.util.Locale;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Sovelluksen käynnistäjä.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class Main extends Application {
	
	/**
	 * Näytetään käyttöliittymä.
	 */
	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(new StackPane());
		Locale locale = new Locale("en","GB");
	    
	    SessionManager sessionManager = new SessionManager(scene, locale);
	    sessionManager.valitseNakyma("-1");

	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	/**
	 * Käynnistetään JavaFX sovellus.
	 * @param args argumentit
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}

























