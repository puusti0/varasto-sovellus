package com.ro8.varastosofta.application.view;

import com.ro8.varastosofta.application.model.Tuote;
import com.ro8.varastosofta.application.model.Validaattori;
import com.ro8.varastosofta.database.TuoteAccessObjectHibernate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class LisaaUusiTuoteController {
	
	@FXML
	private TextField idTextField;
	@FXML
	private TextField nimiTextField;
	@FXML
	private TextField lkmTextField;
		
	private TuoteAccessObjectHibernate dao;
	
	public LisaaUusiTuoteController() {
		dao = new TuoteAccessObjectHibernate();
	}
	
	/**
	 * Napin painallus lisää tuotteen tiedot tietokantaan mikäli annetut tiedot
	 * ovat oikein muutoin kehoittaa korjaamaan tiedot.
	 */
	@FXML
	private void lisaaButtonPainettu() {
		
		
		if(Validaattori.onkoLisattavaTuoteValidi(this.idTextField.getText().toString(),
				this.nimiTextField.getText().toString(), this.lkmTextField.getText().toString())) {
			
			Tuote uusi = new Tuote(Integer.parseInt(this.idTextField.getText().toString()), this.nimiTextField.getText().toString(), Integer.parseInt(this.lkmTextField.getText().toString()));
			this.dao.lisaaTuote(uusi);
			
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erhe Ilmoitus");
			alert.setHeaderText("Annetuissa tiedoissa virheitä");
			alert.setContentText("Tarkista, että yksikään kenttä ei ole tyhjä."
					+ "\nId-kentässä ja Lkm-kentässä on vain numeroita."
					+ "\nNimi-kentän teksti on korkeintaan 20 merkkiä pitkä");

			alert.showAndWait();
		}
		
		
		
	}
	
	/**
	 * Napin painaminen validoi poistettavan tuotteen ID:n ja poistaa kyseisen 
	 * tuotteen tietokannasta sekä antaa ilmoituksen jos poisto onnistui tai tai.
	 */
	@FXML
	private void poistaTuoteButtonPainettu() {
		
		// 
		if(Validaattori.onkoPoistettavaIdValidi(this.idTextField.getText().toString()) 
				&& Validaattori.onkoNumero(this.idTextField.getText().toString())) {
			
			
			this.dao.poistaTuote(Integer.parseInt(this.idTextField.getText().toString()));
			
		} else {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erhe Ilmoitus");
			alert.setHeaderText("Annetuissa tiedoissa virheitä");
			alert.setContentText("Tarkista, että Id-kenttä ei ole tyhjä."
					+ "\nId-kentässä on vain numeroita.");

			alert.showAndWait();
			
		}
		
	}
	
	/**
	 * Tyhjentää tekstikentät.
	 */
	@FXML
	public void tyhjennaButtonPainettu() {
		
		this.idTextField.setText("");
		this.nimiTextField.setText("");
		this.lkmTextField.setText("");
		
	}
	
}