package com.ro8.varastosofta.application.controller;

import com.ro8.varastosofta.application.Main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * Pääkäyttöliittymän kontrolleri
 */
public class MainViewController {
	
	private Main main;
	
	/**
	 * Asettaa viittauksen Main luokkaa.
	 * 
	 * @param main
	 */
	public void setMain(Main main) {
		
		this.main = main;
		
	}
	
	/**
	 * Asettaa LisääUusiTuoteView:n näytön keskelle.
	 */
	@FXML
	private void setLisaaUusiTuoteViewKeskelle() {
		this.main.showLisaaUusiTuoteView();
	}
	
	/**
	 * Asetetaan tuotelistaus borderpanelin keskelle
	 */
	public void setTuoteListausViewKeskelle() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/TuoteListaus.fxml"));
			AnchorPane view = (AnchorPane)loader.load();
			
			this.main.getRoot().setCenter(view);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
				
	}
	
	/**
	 * Sulkee ohjelman.
	 */
	public void suljeOhjelma() {
		
		Platform.exit();
		
	}

}
