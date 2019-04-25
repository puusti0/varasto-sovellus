package com.ro8.varastosofta.application.controller;

import java.util.Locale;
import java.util.ResourceBundle;

import com.ro8.varastosofta.application.IController;
import com.ro8.varastosofta.application.Main;
import com.ro8.varastosofta.application.SessionManager;
import com.ro8.varastosofta.application.model.Ilmoitukset;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * Johtaja käyttäjän sivun kontrolleri.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle.
 */
public class JohtajaViewController implements IController {
	
	@FXML
	private BorderPane rootPane;
	
	private SessionManager sessionManager;
	private Locale locale;
	
	/**
	 * Alustetaan keskunäkymä LisaaKayttajaViewlla.
	 */
	@FXML
	private void initialize() {
	
	}
	
	/**
	 * Käsitellään valikon "Kirjaudu ulos"-valinta.
	 */
	@FXML
	protected void kasitteleKirjauduUlos() {
		
		
		if(Ilmoitukset.uloskirjautumisenVarmistus()) {
			
			this.sessionManager.kirjauduUlos();
			
		}
	}
	
	/**
	 * Käsitellään valikon "Lopeta"-valinta.
	 */
	@FXML
	private void kasitteleLopeta() {
		
		
		if(Ilmoitukset.ohjelmanLopetusVarmistus()) {
			
			this.sessionManager.lopeta();
			
		}
	}
	
	/**
	 * Käsitellään valikon "Kirjaudu ulos"-valinta.
	 */
	@FXML
	protected void kasitteleLisaaKayttaja() {
		aktivoiNakyma("LisaaKayttaja.fxml");
	}
	
	/**
	 * Käsitellään valikon "Tuoteryhmat"-valinta.
	 */
	@FXML
	protected void kasitteleTuoteryhmat() {
		aktivoiNakyma("TuoteryhmatView.fxml");
	}
	
	/**
	 * Asetetaan näkymä päänäkymän keskelle.
	 * @param view asetettavan näkymän nimi
	 */
	public void aktivoiNakyma(String view) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setResources(ResourceBundle.getBundle("MessagesBundle", this.locale));
			loader.setLocation(Main.class.getResource("view/" + view));
			Parent nakyma = (Parent)loader.load();
			this.rootPane.setCenter(nakyma);	
		} catch(Exception e) {
			e.printStackTrace();
		}			
	}

	/**
	 * Alustetaan istunto.
	 */
	@Override
	public void initSession(SessionManager sessionManager, String sessionID, Locale locale) {
		this.sessionManager = sessionManager;
		this.locale = locale;
		aktivoiNakyma("LisaaKayttaja.fxml");

	}
	
}
