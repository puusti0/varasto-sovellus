package com.ro8.varastosofta.application;

import com.ro8.varastosofta.application.controller.KirjauduViewController;
import com.ro8.varastosofta.application.controller.MainViewController;
import com.ro8.varastosofta.interfaces.INakymaController;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Istunnon hallinta.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class Istunto {
	
	private Scene scene;
	private INakymaController kontrolleri;
	private String sessionID;
	private Locale kieli;
	
	/**
	 * Istunnon kontrolleri.
	 * @param scene 
	 */
	public Istunto(Scene scene) {
		this.scene = scene;
		this.sessionID = "";
		this.kieli = new Locale("en","GB");
	}	
	
	public Locale getKieli() {
		return kieli;
	}

	public void setKieli(Locale kieli) {
		this.kieli = kieli;
	}
	
	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	/**
	 * Kirjautuneen käyttäjän näkymä.
	 * @param sessionID yksilöity session id
	 */
	public void valitseNakyma() {
		String[] session = this.getSessionID().split("-");
		String rooli = session[0];
		switch(rooli) {
			case "Varastotyöntekijä":
				this.kontrolleri = new MainViewController(new VarastotyontekijaMenuValikkoTehdas());
				naytaNakyma(this.getSessionID(), "MainView.fxml", 1);
				break;
			case "Johtaja":
				this.kontrolleri = new MainViewController(new JohtajaMenuValikkoTehdas());
				naytaNakyma(this.getSessionID(), "MainView.fxml", 2);
				break;
			default:
				this.kontrolleri = new KirjauduViewController(this);
				naytaNakyma("0", "KirjauduView.fxml", -1);
		}
	}
	
	/**
	 * Valitaan sovelluksessa käytettävä kieli.
	 */
	public void valitseKieli(String kielivalinta) {
		switch(kielivalinta) {
			case "Suomi":
				this.setKieli(new Locale("fi", "FI"));
				break;
			case "Malesia":
				this.setKieli(new Locale("fi", "FI"));
				break;
			default:
				this.setKieli(new Locale("en","GB"));
		}
		this.valitseNakyma();
	}
	
	/**
	 * Uloskirjautumisen jälkeen näytetään kirjautumissivu.
	 */
	public void kirjauduUlos() {
		valitseNakyma();
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
	public void naytaNakyma(String sessionID, String view, int luku) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(this.kontrolleri);
			loader.setResources(ResourceBundle.getBundle("MessagesBundle", this.kieli));
			loader.setLocation(Paaohjelma.class.getResource("view/" + view));
			Parent nakyma = (Parent)loader.load();
			this.kontrolleri.initSession(this, sessionID);
			this.scene.setRoot(nakyma);
		} catch(Exception e) {
			e.printStackTrace();
		}			
	}
}