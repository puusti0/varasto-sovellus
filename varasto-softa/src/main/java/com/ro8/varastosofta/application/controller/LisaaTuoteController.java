package com.ro8.varastosofta.application.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ro8.varastosofta.application.IPopupController;
import com.ro8.varastosofta.application.model.Tuote;
import com.ro8.varastosofta.application.model.Tuoteryhma;
import com.ro8.varastosofta.application.model.Validaattori;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.TuoteDao;
import com.ro8.varastosofta.database.TuoteryhmaDao;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Kontrolleri tuotteen lisäämiseen.
 */
public class LisaaTuoteController implements IPopupController {
	
	@FXML
	private TextField idTextField;
	@FXML
	private TextField nimiTextField;
	@FXML
	private TextField lkmTextField;
	@FXML
	private ComboBox<String> tuoteryhmaComboBox;
		
	private Dao<Tuote, Integer> tuotedao;
	private Dao<Tuoteryhma, Integer> tuoteryhmadao;
	
	private List<Tuoteryhma> ryhmat;
	private HashMap<String, Integer> tuoteryhmat;
	
	/**
	 * Tuotteen lisäys konstruktori.
	 */
	public LisaaTuoteController() {
		this.tuotedao = new TuoteDao();
		this.tuoteryhmadao = new TuoteryhmaDao();
		this.tuoteryhmat = new HashMap<String, Integer>();
		try {
			this.ryhmat = this.tuoteryhmadao.listaa();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Tuoteryhma tuoteryhma : this.ryhmat) {
			this.tuoteryhmat.put(tuoteryhma.getNimi(), tuoteryhma.getId());
		}
		
	}
	
	/**
	 * Alustetaan JavaFX komponentit
	 */
	@FXML
	public void initialize() {
		this.tuoteryhmaComboBox.getItems().removeAll(tuoteryhmaComboBox.getItems());
		this.tuoteryhmaComboBox.getItems().add("Valitse");
		for(Tuoteryhma tuoteryhma : this.ryhmat) {
			this.tuoteryhmaComboBox.getItems().add(tuoteryhma.getNimi());
		}
		this.tuoteryhmaComboBox.getSelectionModel().select("Valitse");
	}
	
	
	/**
	 * Napin painallus lisää tuotteen tiedot tietokantaan mikäli annetut tiedot
	 * ovat oikein muutoin kehoittaa korjaamaan tiedot.
	 */
	@FXML
	private void lisaaButtonPainettu() {
		if(Validaattori.onkoLisattavaTuoteValidi(this.idTextField.getText().toString(), this.nimiTextField.getText().toString(), this.lkmTextField.getText().toString())
				&& Validaattori.onkoTuoteryhmaValidi(this.tuoteryhmaComboBox.getValue())) {
			
			try {
				Tuoteryhma tuoteryhma = this.tuoteryhmadao.hae(this.tuoteryhmat.get(this.tuoteryhmaComboBox.getValue()));
				Tuote uusi = new Tuote(Integer.parseInt(this.idTextField.getText().toString()), this.nimiTextField.getText().toString(), Integer.parseInt(this.lkmTextField.getText().toString()), tuoteryhma);
				this.tuotedao.lisaa(uusi);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

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
			
			
			try {
				this.tuotedao.poista(Integer.parseInt(this.idTextField.getText().toString()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
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
		this.tuoteryhmaComboBox.getSelectionModel().select("Valitse");
	}

	@Override
	public void setObject(Object tuote) {
		Tuote tuoteX = (Tuote)tuote;
		this.idTextField.setText(tuoteX.getId() + "");
		this.nimiTextField.setText(tuoteX.getNimi());
		this.lkmTextField.setText(tuoteX.getLkm() + "");
		this.tuoteryhmaComboBox.getSelectionModel().select("Valitse");
	}
	
}