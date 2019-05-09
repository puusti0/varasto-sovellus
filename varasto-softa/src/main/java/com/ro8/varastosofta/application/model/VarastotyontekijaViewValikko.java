package com.ro8.varastosofta.application.model;

import java.util.ResourceBundle;

import com.ro8.varastosofta.application.controller.MainViewController;
import com.ro8.varastosofta.interfaces.INakymaController;
import com.ro8.varastosofta.interfaces.IViewValikko;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Varastotyöntekijän näkymävalikko.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle.
 */
public class VarastotyontekijaViewValikko implements IViewValikko {
	
	private Menu valikko;
	private MainViewController kontrolleri;
	private ResourceBundle kaannokset;
	
	/**
	 * Varastotyöntekijän kontrolleri.
	 * @param kontrolleri
	 */
	public VarastotyontekijaViewValikko(INakymaController kontrolleri, ResourceBundle kaannokset) {
		this.kontrolleri = (MainViewController)kontrolleri;
		this.kaannokset = kaannokset;
		MenuItem tuote = new MenuItem(this.kaannokset.getString("navigation.addProduct"));
		tuote.setOnAction(tuoteLisays);
		MenuItem tuotteet = new MenuItem(this.kaannokset.getString("navigation.productList"));  
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
			getKontrolleri().aktivoiNakyma("LisaaTuote.fxml");
        } 
    };
    
    EventHandler<ActionEvent> tuotelistaus = new EventHandler<ActionEvent>() {
    	@Override 
        public void handle(ActionEvent e) 
        { 
        	getKontrolleri().aktivoiNakyma("TuoteListaus.fxml");
        } 
    };

}