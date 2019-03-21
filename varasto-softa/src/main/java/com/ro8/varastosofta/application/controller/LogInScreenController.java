package com.ro8.varastosofta.application.controller;

import com.ro8.varastosofta.application.SessionManager;
import com.ro8.varastosofta.application.model.Kayttaja;
import com.ro8.varastosofta.application.model.Rooli;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.KayttajaDao;
import com.ro8.varastosofta.database.RooliDao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Kirjautumisruudulle kontrolleri.
 * 
 * @author Riina Antikainen, Tuukka Mytty, Janne Valle.
 *
 */
public class LogInScreenController {
	
	@FXML
	private TextField tunnusTextField;
	@FXML
	private TextField salasanaTextField;
	@FXML
	private Button kirjauduButton;
	
	private static int sessionID = 1;
	private Dao<Rooli, Integer> roolidao;
	private Dao<Kayttaja, Integer> kayttajadao;
	
	public LogInScreenController() {
		this.roolidao = new RooliDao();
		this.kayttajadao = new KayttajaDao();
	}
	
	/**
	 * Tyhjennä napin painallus asettaa tunnus ja salasana kentät tyhjiksi.
	 */
	/**@FXML
	private void handleKirjaudu() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainView.fxml"));
			final Pane root = (Pane)loader.load();
			// final MainViewController kontrolleri = (MainViewController)loader.getController();	
			Stage mainStage = new Stage();
			final Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../css/application.css").toExternalForm());
	        mainStage.setScene(scene);
	        mainStage.setTitle("VarastoSofta");
			mainStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	 * Tyhjennä napin painallus asettaa tunnus ja salasana kentät tyhjiksi.
	 */
	@FXML
	private void tyhjennaNappiaPainettu() {
		this.tunnusTextField.setText("");
		this.salasanaTextField.setText("");
	}
	
	/**
	 * Lopetus napin painallus sulkee ohjelman.
	 */
	@FXML
	private void lopetaNappiaPainettu() {
		
		Platform.exit();
		
	}
	
	/**
	 * 
	 */
	private String authorize() {
	    return 
	      "user".equals(this.tunnusTextField.getText()) && "password".equals(this.salasanaTextField.getText()) 
	            ? generateSessionID() 
	            : null;
	}
	
	/**
	 * Luodaan sessiolle yksilöllinen id
	 * @return session id
	 */
	private String generateSessionID() {
		    sessionID++;
		    return "Session" + sessionID;
	}
	
	/**
	 * 
	 * @param sessionManager
	 */
	public void initSessionManager(final SessionManager sessionManager) {
		 this.kirjauduButton.setOnAction(new EventHandler<ActionEvent>() {
		      @Override public void handle(ActionEvent event) {
		        String sessionID = authorize();
		        if (sessionID != null) {
		          sessionManager.authenticated(sessionID);
		        }
		      }
		 });
	}
	
}
