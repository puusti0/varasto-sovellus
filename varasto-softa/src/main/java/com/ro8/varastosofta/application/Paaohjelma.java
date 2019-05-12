package com.ro8.varastosofta.application;

import com.ro8.varastosofta.application.controller.KirjauduController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Sovelluksen käynnistäjä.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class Paaohjelma extends Application {
	
	/**
	 * Avataan kirjautumisikkuna.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Kaannokset kaannokset = Kaannokset.getInstance();
			KirjauduController kontrolleri = new KirjauduController(primaryStage);
			FXMLLoader loader = new FXMLLoader();
			loader.setController(kontrolleri);
			loader.setResources(kaannokset.haeKielitiedosto());
			loader.setLocation(Paaohjelma.class.getResource("view/Kirjaudu.fxml"));
			Parent nakyma = loader.load();
			Scene scene = new Scene(nakyma);
			primaryStage.getIcons().add(new Image("/Kuvat/Logo.PNG"));
			primaryStage.setTitle("Procu");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Käynnistetään JavaFX sovellus.
	 * @param args komentorivin argumentit
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}