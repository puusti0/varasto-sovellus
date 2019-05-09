package com.ro8.varastosofta.application;

import com.ro8.varastosofta.application.controller.KirjauduController;
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
	private String istuntoID;
	private Locale kieli;
	
	/**
	 * Istunnon kontrolleri.
	 * @param scene istunnon näyttämö
	 */
	public Istunto(Scene scene) {
		this.scene = scene;
		this.istuntoID = "";
		this.kieli = new Locale("en","GB");
	}	
	
	/**
	 * Palautetaan kielivalinta.
	 * @return kielivalinta
	 */
	public Locale getKieli() {
		return kieli;
	}

	/**
	 * Asetetaan kielivalinta.
	 * @param kieli kielivalinta
	 */
	public void setKieli(Locale kieli) {
		this.kieli = kieli;
	}
	
	/**
	 * Palautetaan istunnon ID.
	 * @return istunnon ID
	 */
	public String getSessionID() {
		return this.istuntoID;
	}

	/**
	 * Asetetaan istunnon iD.
	 * @param istuntoID istunnon IDs
	 */
	public void setSessionID(String istuntoID) {
		this.istuntoID = istuntoID;
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
				naytaNakyma(this.getSessionID(), "MainView.fxml");
				break;
			case "Johtaja":
				this.kontrolleri = new MainViewController(new JohtajaMenuValikkoTehdas());
				naytaNakyma(this.getSessionID(), "MainView.fxml");
				break;
			default:
				this.kontrolleri = new KirjauduController(this);
				naytaNakyma("0", "Kirjaudu.fxml");
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
				this.setKieli(new Locale("zsm", "MY"));
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
		this.istuntoID = "";
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
	 * @param istuntoID istunnon yksilöivä tunnus
	 * @param näkymän nimi
	 */
	public void naytaNakyma(String istuntoID, String nimi) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(this.kontrolleri);
			loader.setResources(ResourceBundle.getBundle("MessagesBundle", this.kieli, new UTF8Control()));
			loader.setLocation(Paaohjelma.class.getResource("view/" + nimi));
			Parent nakyma = (Parent)loader.load();
			this.kontrolleri.initSession(this, istuntoID);
			this.scene.setRoot(nakyma);
		} catch(Exception e) {
			e.printStackTrace();
		}			
	}
}