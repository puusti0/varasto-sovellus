package com.ro8.varastosofta.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Sovelluksen käynnistäjä.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class Paaohjelma extends Application {
	
	/**
	 * Näytetään käyttöliittymä.
	 */
	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(new StackPane()); 
	    Istunto istunto = new Istunto(scene);
	    istunto.valitseNakyma();
	    primaryStage.getIcons().add(new Image("/Kuvat/Logo.PNG"));
	    primaryStage.setTitle("Procu");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	/**
	 * Käynnistetään JavaFX sovellus.
	 * @param args komentorivin argumentit
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}