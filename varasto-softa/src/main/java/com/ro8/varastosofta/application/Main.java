package com.ro8.varastosofta.application;
	
import com.ro8.varastosofta.application.controller.MainViewController;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * Sovelluksen käynnistäminen
 */
public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane root;
	private MainViewController mainViewController;
	
	@Override
	public void start(Stage primaryStage) {	
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("VarastoSofta");		
		initRoot();
		showLisaaUusiTuoteView();
	}
	
	
	/**
	 * Pohjustaa root näkymän.
	 */
	public void initRoot() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainView.fxml"));
			this.root = (BorderPane)loader.load();
			this.mainViewController = loader.getController();
			this.mainViewController.setMain(this);
			Scene scene = new Scene(this.root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * LisaaUusiTuote keskelle näkymää.
	 */
	public void showLisaaUusiTuoteView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./view/LisaaTuoteView.fxml"));
			AnchorPane view = (AnchorPane)loader.load();
			this.root.setCenter(view);	
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Palauttaa viittauksen rootiin.
	 * @return BorderPane pohja
	 */
	public BorderPane getRoot() {		
		return this.root;		
	}
	
	/**
	 * Käynnistetään ohjelma
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

























