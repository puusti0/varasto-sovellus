package com.ro8.varastosofta.application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Sovelluksen käynnistäjä.
 */
public class Main extends Application {
	
	/**
	 * Näytetään käyttöliittymä.
	 */
	@Override
	public void start(Stage primaryStage) {
		
		Scene scene = new Scene(new StackPane());
	    
	    SessionManager sessionManager = new SessionManager(scene);
	    sessionManager.naytaNakyma("0", "LogInScreen.fxml");

	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
		//Stage stage = primaryStage;
		/**try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainView.fxml"));
			final Pane root = (Pane)loader.load();
			// final MainViewController kontrolleri = (MainViewController)loader.getController();	
			final Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("VarastoSofta");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//initLoginScreen(stage);
		// initVarastoScreen(stage);
	}
	
	public void initVarastoScreen(Stage primaryStage) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainView.fxml"));
			final Pane root = (Pane)loader.load();
			// final MainViewController kontrolleri = (MainViewController)loader.getController();	
			final Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("VarastoSofta");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void initLoginScreen(Stage primaryStage) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LogInScreen.fxml"));
			final Pane root = (Pane)loader.load();
			// final MainViewController kontrolleri = (MainViewController)loader.getController();	
			final Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("VarastoSofta");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Käynnistetään JavaFX sovellus.
	 * @param args argumentit
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}

























