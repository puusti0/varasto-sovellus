package com.ro8.varastosofta.application.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import com.ro8.varastosofta.application.model.Ilmoitukset;
import com.ro8.varastosofta.application.model.Kayttaja;
import com.ro8.varastosofta.application.model.Rooli;
import com.ro8.varastosofta.application.model.Tooltipit;
import com.ro8.varastosofta.application.model.Validaattori;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.KayttajaDao;
import com.ro8.varastosofta.database.RooliDao;
import com.ro8.varastosofta.interfaces.IController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

/**
 * Käyttäjän lisäys lomakkeen kontrolleri.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class LisaaKayttajaController implements IController {
	
	private Dao<Kayttaja, Integer> kayttajadao;
	private Dao<Rooli, Integer> roolidao;
	private List<Rooli> roolit;
	private HashMap<String, Rooli> rooliryhmat;
	private ResourceBundle kaannokset;
	private Ilmoitukset ilmoitukset;
	private Validaattori validaattori;
	private Tooltipit tooltipit;
	
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
	
	/**
	 * Käyttäjänlisäys lomakkeen konstruktori.
	 */
	public LisaaKayttajaController() {
		this.tooltipit = new Tooltipit();
		validaattori = new Validaattori();
		this.ilmoitukset = new Ilmoitukset();
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
		
		if (validaattori.onkoLisattavaKayttajaValidi(kayttajatunnus, salasana)) {
			
			try {
				Kayttaja uusi = new Kayttaja(kayttajatunnus, salasana, rooli);
				this.kayttajadao.lisaa(uusi);
				this.ilmoitukset.informaatioAlertti("Information Dialog", null, this.kaannokset.getString("alert.user.succesfulAdd"));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			this.ilmoitukset.informaatioAlertti("Information Dialog", null, this.kaannokset.getString("alert.user.failedAdd"));
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
		this.rooliComboBox.getSelectionModel().select(this.kaannokset.getString("button.choose"));
	}
	
	/**
	 * Poista napin painallus jolla poistetaan käyttäjä tietokannasta.
	 */
	@FXML
	private void poistaButtonPainettu() {
		if(this.ilmoitukset.confirmaatioAlertti("Confirmation Dialog", null, this.kaannokset.getString("alert.user.deleteUser"))) {
			this.ilmoitukset.informaatioAlertti("Information Dialog", null, this.kaannokset.getString("alert.user.succesfulRemove"));		
		} else {
			this.ilmoitukset.informaatioAlertti("Information Dialog", null, this.kaannokset.getString("alert.user.failedRemove"));	
		}
	}
	
	/**
	 * Lisää Tooltipit komponennteihin.
	 */
	public void lisaaTooltipitKomponentteihin(){
		tooltipit.asetaTooltip(this.kayttajatunnusTextField, "Set the username.");
		tooltipit.asetaTooltip(this.salasanaTextField, "Set the password");
		tooltipit.asetaTooltip(this.salasanaUudelleenTextField, "Set the password again");
		tooltipit.asetaTooltip(this.rooliComboBox, "Set the usergroup.");
		tooltipit.asetaTooltip(this.lisaaButton, "Add a user to the database.");
		tooltipit.asetaTooltip(this.poistaButton, "Remove a user from the database.");
		tooltipit.asetaTooltip(this.tyhjennaButton, "Clear the input fields.");
	}

	@Override
	public void setKaannokset(ResourceBundle kaannokset) {
		this.kaannokset = kaannokset;
	}

	@Override
	public void init() {
		
	}
	
	
}





















