package com.ro8.varastosofta.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Kirjautuneen käyttäjän näkymien hallinta.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class SessionManager {
	
	private Scene scene;
	
	/**
	 * SessionManager kontrolleri.
	 * @param scene 
	 */
	public SessionManager(Scene scene) {
		this.scene = scene;
	}
	
	/**
	 * Kirjautuneen käyttäjän näkymä.
	 * @param sessionID yksilöity session id
	 */
	public void authenticated(String sessionID) {
		naytaNakyma(sessionID, "MainView.fxml");
	}
	
	/**
	 * Uloskirjautumisen jälkeen näytetään kirjautumissivu.
	 */
	public void logout() {
	    naytaNakyma("0", "LogInScreen.fxml");
	}

	/**
	 * Näkymän näyttämiseen tarvittavat toimenpiteet.
	 */
	public void naytaNakyma(String sessionID, String view) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/" + view));
			Parent nakyma = (Parent)loader.load();
			this.scene.setRoot(nakyma);
			IController controller = (IController)loader.getController();
			controller.initSession(this, sessionID);
		} catch(Exception e) {
			e.printStackTrace();
		}			
	}
}
