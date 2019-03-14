package com.ro8.varastosofta.application.controller;

import java.io.IOException;

import com.ro8.varastosofta.application.Main;
import com.ro8.varastosofta.application.controller.MainViewController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * popup-ikkuna tietojen näyttämiseen. 
 * @author Riina Antikainen
 */
public class PopupController {
	
	private Stage popupStage;
	private BorderPane root;
	
	/**
	 * Popup-ikkunan konstruktori
	 * @param title oikeassa yläreunassa näkyvä nimi
	 */
	public PopupController(String title) {
		this.popupStage = new Stage();
		this.popupStage.initModality(Modality.APPLICATION_MODAL);
		this.popupStage.setTitle(title);
		this.initRoot();
	}
	
	/**
	 * Pohjustaa popup-ikkunan pohjan.
	 */
	private void initRoot() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(PopupController.class.getResource("view/PopupView.fxml"));
			this.root = (BorderPane)loader.load();
			Scene scene = new Scene(this.root, 400, 300);
			scene.getStylesheets().add(getClass().getResource("popup.css").toExternalForm());
			this.popupStage.setScene(scene);
			this.popupStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	public void open(String view, int leveys, int korkeus, Object objekti) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(PopupController.class.getResource("./../view/" + view));
			AnchorPane view1 = (AnchorPane)loader.load();
			this.root.setCenter(view1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
