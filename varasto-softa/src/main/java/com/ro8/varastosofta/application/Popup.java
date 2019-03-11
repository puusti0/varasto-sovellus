package com.ro8.varastosofta.application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup {
	
	private Stage popupStage;
	private BorderPane root;
	
	public Popup(String title) {
		this.popupStage = new Stage();
		this.popupStage.initModality(Modality.APPLICATION_MODAL);
		this.popupStage.setTitle(title);
	}
	
	public void open(String view, int leveys, int korkeus, Object objekti) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/" + view));
		try {
			this.root = (BorderPane)loader.load();
			IController controller = loader.<IController>getController();
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
