package com.ro8.varastosofta.application.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ro8.varastosofta.application.model.Ilmoitukset;
import com.ro8.varastosofta.application.model.Kayttaja;
import com.ro8.varastosofta.application.model.Rooli;
import com.ro8.varastosofta.application.model.Tooltipit;
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
import javafx.scene.control.Button;

/**
 * Käyttäjän lisäys lomakkeen kontrolleri.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class LisaaKayttajaController {
	
	@FXML
	private TextField kayttajatunnusTextField;
	@FXML
	private TextField salasanaTextField;
	@FXML
	private TextField salasanaUudelleenTextField;
	@FXML
	private ComboBox<String> rooliComboBox;
	@FXML
	private Button lisaaButton;
	@FXML 
	private Button poistaButton;
	@FXML
	private Button tyhjennaButton;
	
	
	private Dao kayttajadao;
	private Dao roolidao;
	private List<Rooli> roolit;
	private HashMap<String, Rooli> rooliryhmat;
	
	/**
	 * Käyttäjänlisäys lomakkeen konstruktori.
	 */
	public LisaaKayttajaController() {
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
		
		lisaaTooltipitKomponentteihin();
		
	}
	
	/**
	 * Uuden käyttäjän lisääminen tietokantaan.
	 */
	@FXML
	private void kasitteleLisaa() {
		String kayttajatunnus = this.kayttajatunnusTextField.getText();
		String salasana = this.salasanaTextField.getText();
		Rooli rooli = this.rooliryhmat.get(this.rooliComboBox.getValue());
		
		if (Validaattori.onkoLisattavaKayttajaValidi(kayttajatunnus, salasana)) {
			try {
				Kayttaja uusi = new Kayttaja(kayttajatunnus, salasana, rooli);
				this.kayttajadao.lisaa(uusi);
				
				Ilmoitukset.kayttajaLisattyOnnistuneestiIlmo();
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		} else {
			Ilmoitukset.kayttajaLisaysEiOnnistunutIlmo();

		}
			
		tyhjennaKentat(this.kayttajatunnusTextField, this.salasanaTextField, this.salasanaUudelleenTextField);
	}
	
	/**
	 * Tyhjentää tekstikentät.
	 */
	public void tyhjennaKentat(TextField tunnus, TextField salasana, TextField salasanaUudestaan) {
		
		tunnus.setText("");
		salasana.setText("");
		salasanaUudestaan.setText("");
	}
	
	/**
	 * Tyhjennä napin painallus tyhjentää tekstikentät.
	 */
	@FXML
	private void tyhjennaButtonPainettu() {
		tyhjennaKentat(this.kayttajatunnusTextField, this.salasanaTextField, this.salasanaUudelleenTextField);
		this.rooliComboBox.getSelectionModel().select("Valitse");

	}
	
	/**
	 * Poista napin painallus jolla poistetaan käyttäjä tietokannasta.
	 */
	@FXML
	private void poistaButtonPainettu() {
		
		if(Ilmoitukset.kayttajanPoistonVarmistus()) {
			
			//TODO: tähän käyttäjän poistamisen toiminnallisuus.
			
			Ilmoitukset.kayttajaPoistettuOnnistuneesti();
			
		} else {
			
			//TODO: ja tähän myös käyttäjän poistamisen toiminnallisuus.
			Ilmoitukset.kayttajaPoistettuEiOnnistunut();
			
		}
		
		
	}
	
	/**
	 * Lisää Tooltipit komponennteihin.
	 */
	public void lisaaTooltipitKomponentteihin(){
		
		Tooltipit.asetaTooltip(this.kayttajatunnusTextField, "Set the username.");
		Tooltipit.asetaTooltip(this.salasanaTextField, "Set the password");
		Tooltipit.asetaTooltip(this.salasanaUudelleenTextField, "Set the password again");
		Tooltipit.asetaTooltip(this.rooliComboBox, "Set the usergroup.");
		Tooltipit.asetaTooltip(this.lisaaButton, "Add a user to the database.");
		Tooltipit.asetaTooltip(this.poistaButton, "Remove a user from the database.");
		Tooltipit.asetaTooltip(this.tyhjennaButton, "Clear the input fields.");
		
	}
	
	
}





















