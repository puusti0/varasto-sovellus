package com.ro8.varastosofta.application.model;

import com.ro8.varastosofta.application.controller.MainViewController;
import com.ro8.varastosofta.interfaces.INakymaController;
import com.ro8.varastosofta.interfaces.IViewValikko;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class VarastotyontekijaViewValikko implements IViewValikko {
	
	private Menu valikko;
	private MainViewController kontrolleri;
	
	public VarastotyontekijaViewValikko(INakymaController kontrolleri) {
		this.kontrolleri = (MainViewController)kontrolleri;
		MenuItem tuote = new MenuItem("Add product");
		tuote.setOnAction(tuoteLisays);
		MenuItem tuotteet = new MenuItem("Products");
		tuotteet.setOnAction(tuotelistaus);
		this.valikko = new Menu();
		this.valikko.getItems().add(tuote);
		this.valikko.getItems().add(tuotteet);
	}
	
	public MainViewController getKontrolleri() {
		return kontrolleri;
	}

	@Override
	public Menu getMenu() {
		return this.valikko;
	}
    
    EventHandler<ActionEvent> tuoteLisays = new EventHandler<ActionEvent>() { 
		@Override 
        public void handle(ActionEvent e) 
        { 
			getKontrolleri().aktivoiNakyma("LisaaTuoteView.fxml");
        } 
    };
    
    EventHandler<ActionEvent> tuotelistaus = new EventHandler<ActionEvent>() {
    	@Override 
        public void handle(ActionEvent e) 
        { 
        	getKontrolleri().aktivoiNakyma("TuoteListausView.fxml");
        } 
    };

}
