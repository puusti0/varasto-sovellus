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
 * Johtajan näkymävalikko.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle.
 */
public class JohtajaViewValikko implements IViewValikko {
	
	private Menu valikko;
	private MainViewController kontrolleri;
	private Kaannokset kaannokset;
	
	/**
	 * Johtajan näkymävalikon kontrolleri.
	 * @param kontrolleri
	 */
	public JohtajaViewValikko(Controller kontrolleri) {
		this.kontrolleri = (MainViewController)kontrolleri;
		this.kaannokset = Kaannokset.getInstance();
		MenuItem kayttajat = new MenuItem(this.kaannokset.kaanna("navigation.users"));
		kayttajat.setOnAction(kayttajaNakyma);
		MenuItem tuoteryhmat = new MenuItem(this.kaannokset.kaanna("navigation.categories"));
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
        	getKontrolleri().aktivoiNakyma("Tuoteryhmat.fxml");
        } 
    };
	
}
