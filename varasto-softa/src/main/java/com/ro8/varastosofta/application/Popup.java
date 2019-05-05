package com.ro8.varastosofta.application;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.ro8.varastosofta.application.model.Ilmoitukset;
import com.ro8.varastosofta.interfaces.IController;
import com.ro8.varastosofta.interfaces.IPopupController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Yksilöllinen popup ikkuna.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class Popup implements IController{
	
	private Stage popupStage;
	private Parent root;
	private ResourceBundle kaannokset;
	private Ilmoitukset ilmoitukset;
	
	
	/**
	 * Popup konstruktori.
	 * @param title näytön oikeassa yläkulmassa näkyvä nimi
	 */
	public Popup(String title) {
		this.ilmoitukset = new Ilmoitukset();
		this.popupStage = new Stage();
		this.popupStage.initModality(Modality.APPLICATION_MODAL);
		this.popupStage.setTitle(title);
	}
	
	/**
	 * Avataan popup-ikkuna.
	 * @param view näkymä
	 * @param leveys leveys
	 * @param korkeus leveys
	 * @param objekti Popup:n käyttämät luokat
	 */
	public void avaa(String view, int leveys, int korkeus, Object objekti) {
		FXMLLoader loader = new FXMLLoader();
		loader.setResources(ResourceBundle.getBundle("MessagesBundle", new Locale("en","GB")));
		loader.setLocation(Paaohjelma.class.getResource("view/" + view));
		try {
			this.root = (Parent)loader.load();
			IPopupController controller = loader.<IPopupController>getController();
			controller.setObject(objekti);
			controller.asetaTeksti();
			Scene scene = new Scene(root, leveys, korkeus);
			this.popupStage.setScene(scene);
			this.popupStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Suljetaan popup-ikkuna.
	 */
	public void sulje() {
		this.popupStage.close();
	}

	@Override
	public void setKaannokset(ResourceBundle kaannokset) {
		this.kaannokset = kaannokset;
	}

}
