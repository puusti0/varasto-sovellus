package com.ro8.varastosofta.application.model;

import com.ro8.varastosofta.application.controller.MainViewController;
import com.ro8.varastosofta.interfaces.INakymaController;
import com.ro8.varastosofta.interfaces.IViewValikko;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class JohtajaViewValikko implements IViewValikko {
	
	private Menu valikko;
	private MainViewController kontrolleri;
	
	public JohtajaViewValikko(INakymaController kontrolleri) {
		this.kontrolleri = (MainViewController)kontrolleri;
		MenuItem kayttajat = new MenuItem("Users");
		kayttajat.setOnAction(kayttajaNakyma);
		MenuItem tuoteryhmat = new MenuItem("Categories");
		tuoteryhmat.setOnAction(tuoteryhmaNakyma);
		this.valikko = new Menu();
		this.valikko.getItems().add(kayttajat);
		this.valikko.getItems().add(tuoteryhmat);
	}
	
	public MainViewController getKontrolleri() {
		return kontrolleri;
	}

	@Override
	public Menu getMenu() {
		return this.valikko;
	}
	
	EventHandler<ActionEvent> kayttajaNakyma = new EventHandler<ActionEvent>() { 
		@Override 
        public void handle(ActionEvent e) 
        { 
			getKontrolleri().aktivoiNakyma("LisaaKayttaja.fxml");
        } 
    };
    
    EventHandler<ActionEvent> tuoteryhmaNakyma = new EventHandler<ActionEvent>() {
    	@Override 
        public void handle(ActionEvent e) 
        { 
        	getKontrolleri().aktivoiNakyma("TuoteryhmatView.fxml");
        } 
    };
	
}
