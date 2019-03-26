package com.ro8.varastosofta.application.controller;

import com.ro8.varastosofta.application.IController;
import com.ro8.varastosofta.application.Main;
import com.ro8.varastosofta.application.SessionManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class AdminMainViewController implements IController {
	
	@FXML
	private BorderPane rootPane;
	private SessionManager sessionManager;
	
	/**
	 * Käsitellään valikon "Kirjaudu ulos"-valinta.
	 */
	@FXML
	protected void kasitteleKirjauduUlos() {
		this.sessionManager.kirjauduUlos();
	}
	
	/**
	 * Käsitellään valikon "Lopeta"-valinta.
	 */
	@FXML
	private void kasitteleLopeta() {
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
