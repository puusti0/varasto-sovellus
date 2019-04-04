package com.ro8.varastosofta.application;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
	private Locale locale;
	
	/**
	 * SessionManager kontrolleri.
	 * @param scene 
	 */
	public SessionManager(Scene scene, Locale locale) {
		this.scene = scene;
		this.locale = locale;
	}
	
	/**
	 * Kirjautuneen käyttäjän näkymä.
	 * @param sessionID yksilöity session id
	 */
	public void valitseNakyma(String sessionID) {
		String[] session = sessionID.split("-");
		String rooli = session[0];
		switch(rooli) {
			case "Varastotyöntekijä":
				naytaNakyma(sessionID, "VarastotyontekijaView.fxml");
				break;
			case "Myyntiedustaja":
				naytaNakyma(sessionID, "VarastotyontekijaView.fxml");
				break;
			case "Johtaja":
				naytaNakyma(sessionID, "JohtajaView.fxml");
				break;
			default:
				naytaNakyma("0", "KirjauduView.fxml");
		}
	}
	
	/**
	 * Uloskirjautumisen jälkeen näytetään kirjautumissivu.
	 */
	public void kirjauduUlos() {
		valitseNakyma("-1");
	}
	
	/**
	 * Suljetaan sovellus.
	 */
	public void lopeta() {
		Platform.exit();
	}


	/**
	 * Näkymän näyttämiseen tarvittavat toimenpiteet.
	 */
	public void naytaNakyma(String sessionID, String view) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setResources(ResourceBundle.getBundle("MessagesBundle", this.locale));
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
