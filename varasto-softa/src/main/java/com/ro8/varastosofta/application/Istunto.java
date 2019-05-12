package com.ro8.varastosofta.application;

import com.ro8.varastosofta.application.controller.Controller;
import com.ro8.varastosofta.application.controller.KirjauduController;
import com.ro8.varastosofta.application.controller.MainViewController;
import java.util.Locale;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Istunnon hallinta.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class Istunto {
	
	private Stage stage;
	private Controller kontrolleri;
	private String istuntoID;
	private Kaannokset kaannokset;
	
	/**
	 * Istunnon kontrolleri.
	 * @param scene istunnon näyttämö
	 */
	public Istunto(Stage stage, String ID) {
		this.kaannokset = Kaannokset.getInstance();
		this.stage = stage;
		this.istuntoID = ID;
		valitseNakyma();
	}
	
	/**
	 * Palautetaan istunnon tunnus.
	 * @return istunnon yksilöivä tunnus
	 */
	public String getIstuntoID() {
		return istuntoID;
	}

	/**
	 * Määritetään kirjautuneen käyttäjän menu.
	 */
	public void valitseNakyma() {
		String[] istunto = this.getIstuntoID().split("-");
		String rooli = istunto[0];
		switch(rooli) {
			case "Varastotyöntekijä":
				this.kontrolleri = new MainViewController(this, new VarastotyontekijaMenuValikkoTehdas() );
				naytaNakyma("MainView.fxml");
				break;
			case "Johtaja":
				this.kontrolleri = new MainViewController(this, new JohtajaMenuValikkoTehdas() );
				naytaNakyma("MainView.fxml");
				break;
			default:
				this.kontrolleri = new KirjauduController(this.stage);
				naytaNakyma("Kirjaudu.fxml");
		}
	}
	
	/**
	 * Valitaan sovelluksessa käytettävä kieli.
	 * @param kielivalinta valittu kieli
	 */
	public void valitseKieli(String kielivalinta) {
		switch(kielivalinta) {
			case "Suomi":
				this.kaannokset.setKieli((new Locale("fi", "FI")));
				break;
			case "Malaysia":
				this.kaannokset.setKieli((new Locale("zsm", "MY")));
				break;
			default:
				this.kaannokset.setKieli((new Locale("en","GB")));
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
	 * @param näkymän nimi
	 */
	public void naytaNakyma(String nimi) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(this.kontrolleri);
			loader.setResources(this.kaannokset.haeKielitiedosto());
			loader.setLocation(Paaohjelma.class.getResource("view/" + nimi));
			Parent nakyma = (Parent) loader.load();
			Scene scene = stage.getScene();
			if (scene == null) {
				scene = new Scene(nakyma);
				this.stage.setScene(scene);
			} else {
				this.stage.getScene().setRoot(nakyma);
			}
			this.stage.sizeToScene();
		} catch(Exception e) {
			e.printStackTrace();
		}			
	}
}