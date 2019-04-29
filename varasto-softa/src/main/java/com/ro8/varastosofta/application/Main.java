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
		Locale localeGB = new Locale("en","GB");
		Locale localeFI = new Locale("fi", "FI");
		Locale localeENMY = new Locale("en", "MY");
		
		
		// Asetaan locale talteen muita ohjelmien osia varten.
		Lokaali.setLocale(localeGB);
	    
	    SessionManager sessionManager = new SessionManager(scene);
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

























