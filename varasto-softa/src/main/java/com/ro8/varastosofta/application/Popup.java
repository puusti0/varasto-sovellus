package com.ro8.varastosofta.application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Oma popup ikkuna
 */
public class Popup {
	
	private Stage popupStage;
	private Parent root;
	
	/**
	 * Popup kontrolleri
	 * @param title näytön oikeassa yläkulmassa näkyvä nimi
	 */
	public Popup(String title) {
		this.popupStage = new Stage();
		this.popupStage.initModality(Modality.APPLICATION_MODAL);
		this.popupStage.setTitle(title);
	}
	
	/**
	 * Avataan popup
	 * @param view näkymä
	 * @param leveys leveys
	 * @param korkeus leveys
	 * @param objekti Popup:n käyttämät luokat
	 */
	public void open(String view, int leveys, int korkeus, Object objekti) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/" + view));
		try {
			this.root = (Parent)loader.load();
			IPopupController controller = loader.<IPopupController>getController();
			controller.setObject(objekti);
			Scene scene = new Scene(root, leveys, korkeus);
			this.popupStage.setScene(scene);
			this.popupStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
