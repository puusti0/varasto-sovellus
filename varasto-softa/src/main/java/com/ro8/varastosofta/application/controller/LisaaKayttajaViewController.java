package com.ro8.varastosofta.application.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ro8.varastosofta.application.model.Kayttaja;
import com.ro8.varastosofta.application.model.Rooli;
import com.ro8.varastosofta.application.model.Tuote;
import com.ro8.varastosofta.application.model.Tuoteryhma;
import com.ro8.varastosofta.application.model.Validaattori;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.KayttajaDao;
import com.ro8.varastosofta.database.RooliDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LisaaKayttajaViewController {
	
	@FXML
	private TextField kayttajatunnusTextField;
	@FXML
	private TextField salasanaTextField;
	@FXML
	private TextField salasanaUudelleenTextField;
	@FXML
	private ComboBox<String> rooliComboBox;
	
	private Dao kayttajadao;
	private Dao roolidao;
	private List<Rooli> roolit;
	private HashMap<String, Rooli> rooliryhmat;
	
	public LisaaKayttajaViewController() {
		this.kayttajadao = new KayttajaDao();
		this.roolidao = new RooliDao();
		this.rooliryhmat = new HashMap<String, Rooli>();
		try {
			this.roolit = this.roolidao.listaa();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Rooli rooli : this.roolit) {
			this.rooliryhmat.put(rooli.getNimi(), rooli);
		}
	}
	
	/**
	 * Alustetaan JavaFX komponentit
	 */
	@FXML
	public void initialize() {
		this.rooliComboBox.getItems().removeAll(rooliComboBox.getItems());
		this.rooliComboBox.getItems().add("Valitse");
		for(Rooli rooli : this.roolit) {
			this.rooliComboBox.getItems().add(rooli.getNimi());
		}
		this.rooliComboBox.getSelectionModel().select("Valitse");
	}
	
	/**
	 * Uuden käyttäjän lisääminen tietokantaan.
	 */
	@FXML
	private void kasitteleLisaa() {
		String kayttajatunnus = this.kayttajatunnusTextField.getText();
		String salasana = this.salasanaTextField.getText();
		Rooli rooli = this.rooliryhmat.get(this.rooliComboBox.getValue());
		
			try {
				Kayttaja uusi = new Kayttaja(kayttajatunnus, salasana, rooli);
				this.kayttajadao.lisaa(uusi);
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		tyhjennaKentat();
	}
	
	/**
	 * Tyhjentää tekstikentät.
	 */
	public void tyhjennaKentat() {
		this.kayttajatunnusTextField.setText("");
		this.salasanaTextField.setText("");
		this.salasanaUudelleenTextField.setText("");
		this.rooliComboBox.getSelectionModel().select("Valitse");
	}
	
}
