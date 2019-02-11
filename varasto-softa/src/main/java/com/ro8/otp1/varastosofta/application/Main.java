package com.ro8.otp1.varastosofta.application;
	

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane root;
	
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
			this.root = (BorderPane)FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
			Scene scene = new Scene(this.root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Lis�� LisaaUusiTuoteViewn keskelle näkymää.
	 */
	public void showLisaaUusiTuoteView() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/LisaaUusiTuoteView.fxml"));
			AnchorPane view = (AnchorPane)loader.load();
			
			this.root.setCenter(view);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
































