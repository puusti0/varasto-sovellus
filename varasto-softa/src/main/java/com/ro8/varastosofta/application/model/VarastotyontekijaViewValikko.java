package com.ro8.varastosofta.application.model;

import com.ro8.varastosofta.application.Kaannokset;
import com.ro8.varastosofta.application.controller.Controller;
import com.ro8.varastosofta.application.controller.MainViewController;
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
	private Kaannokset kaannokset;
	
	/**
	 * Varastotyöntekijän valikon konstruktori.
	 * @param kontrolleri
	 */
	public VarastotyontekijaViewValikko(Controller kontrolleri) {
		this.kontrolleri = (MainViewController)kontrolleri;
		this.kaannokset = Kaannokset.getInstance();
		MenuItem tuote = new MenuItem(this.kaannokset.kaanna("navigation.addProduct"));
		tuote.setOnAction(tuoteLisays);
		MenuItem tuotteet = new MenuItem(this.kaannokset.kaanna("navigation.productList"));  
		tuotteet.setOnAction(tuotelistaus);
		this.valikko = new Menu();
		this.valikko.getItems().add(tuote);
		this.valikko.getItems().add(tuotteet);
	}
	
	/**
	 * Palautetaan kontrolleri
	 * @return Päänakymän kontrolleri
	 */
	public MainViewController getKontrolleri() {
		return kontrolleri;
	}

	/**
	 * Palautetaan luotu valikko.
	 * @return valikko
	 */
	@Override
	public Menu getMenu() {
		return this.valikko;
	}
    
	/**
	 * Käsitellään tuotteen näyttäminen.
	 */
    EventHandler<ActionEvent> tuoteLisays = new EventHandler<ActionEvent>() { 
		@Override 
        public void handle(ActionEvent e) 
        { 
			getKontrolleri().aktivoiNakyma("LisaaTuote.fxml");
        } 
    };
    
    /**
     * Käsitellään tuotelistaus.
     */
    EventHandler<ActionEvent> tuotelistaus = new EventHandler<ActionEvent>() {
    	@Override 
        public void handle(ActionEvent e) 
        { 
        	getKontrolleri().aktivoiNakyma("TuoteListaus.fxml");
        } 
    };

}
