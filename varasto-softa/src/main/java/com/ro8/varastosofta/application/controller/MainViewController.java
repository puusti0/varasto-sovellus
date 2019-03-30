package com.ro8.varastosofta.application.controller;

import com.ro8.varastosofta.application.IController;
import com.ro8.varastosofta.application.Main;
import com.ro8.varastosofta.application.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * Käyttöliittymän päänäkymä.
 * Käsitellään valikon valinnat.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class MainViewController implements IController {
	
	@FXML
	private BorderPane rootPane;
	@FXML
	private MenuItem lisaaTuoteMenuItem;
	
	private SessionManager sessionManager;
	
	/**
	 * Päänäkymän konstruktori.
	 */
	public MainViewController() {

	}
	
	/**
	 * Alustetaan nakyma siten, että keskelle asetetaan TuoteListausView.
	 */
	@FXML
	private void initialize() {
		
		aktivoiNakyma("TuoteListausView.fxml");
		
	}
	
	/**
	 * Käsitellään valikon "Lisää Tuote"-valinta.
	 */
	@FXML
    protected void handleLisaaTuoteMenuItem() {
		aktivoiNakyma("LisaaTuoteView.fxml");
    }
	
	/**
	 * Käsitellään valikon "Tuotelistaus"-valinta.
	 */
	@FXML
	protected void handleTuotelistausMenuItem() {
		aktivoiNakyma("TuoteListausView.fxml");
	}
	
	
	/**
	 * Käsitellään valikon "Kirjaudu ulos"-valinta.
	 */
	@FXML
	protected void kasitteleKirjauduUlos() {
		this.sessionManager.kirjauduUlos();
	}
	
	/**
	 * Suljetaan sovellus.
	 */
	@FXML
	public void close() {
		this.sessionManager.lopeta();
	}
	
	/**
	 * Asetetaan näkymä päänäkymän keskelle.
	 * @param view asetettavan näkymän nimi
	 */
	public void aktivoiNakyma(String view) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/" + view));
			Parent nakyma = (Parent)loader.load();
			this.rootPane.setCenter(nakyma);		
		} catch(Exception e) {
			e.printStackTrace();
		}			
	}

	@Override
	public void initSession(SessionManager sessionManager, String sessionID) {
		this.sessionManager = sessionManager;
		
	}

}
