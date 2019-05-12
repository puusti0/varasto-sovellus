package com.ro8.varastosofta.application.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import com.ro8.varastosofta.application.model.Kayttaja;
import com.ro8.varastosofta.application.model.Rooli;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.KayttajaDao;
import com.ro8.varastosofta.database.RooliDao;
import com.ro8.varastosofta.application.PasswordEncryptionService;
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
public class LisaaKayttajaController extends Controller {
	
	private Dao<Kayttaja, Integer> kayttajadao;
	private Dao<Rooli, Integer> roolidao;
	private List<Rooli> roolit;
	private HashMap<String, Rooli> rooliryhmat;
	
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
		super();
		this.kayttajadao = new KayttajaDao();
		this.roolidao = new RooliDao();
		this.rooliryhmat = new HashMap<>();
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
	 * Alustetaan JavaFX komponentit.
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
	 * Lisätään vihjeet komponentteihin.
	 */
	@Override
	public void lisaaVihjeetKomponentteihin() {
		this.getVihjeet().aseta(this.kayttajatunnusTextField, "Set the username.");
		this.getVihjeet().aseta(this.salasanaTextField, "Set the password");
		this.getVihjeet().aseta(this.salasanaUudelleenTextField, "Set the password again");
		this.getVihjeet().aseta(this.rooliComboBox, "Set the usergroup.");
		this.getVihjeet().aseta(this.lisaaButton, "Add a user to the database.");
		this.getVihjeet().aseta(this.poistaButton, "Remove a user from the database.");
		this.getVihjeet().aseta(this.tyhjennaButton, "Clear the input fields.");
	}
	
	/**
	 * Uuden käyttäjän lisääminen tietokantaan.
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	@FXML
	private void kasitteleLisaa() throws NoSuchAlgorithmException, InvalidKeySpecException {
		String kayttajatunnus = this.kayttajatunnusTextField.getText();
		String salasana = this.salasanaTextField.getText();
		byte[] suola = PasswordEncryptionService.generateSalt();
		byte[] salattu = PasswordEncryptionService.getEncryptedPassword(salasana, suola);
		Rooli rooli = this.rooliryhmat.get(this.rooliComboBox.getValue());
		
		if (this.getValidaattori().onkoLisattavaKayttajaValidi(kayttajatunnus, salasana)) {
			
			try {
				Kayttaja uusi = new Kayttaja(kayttajatunnus, Base64.getEncoder().encodeToString(salattu),  Base64.getEncoder().encodeToString(suola), rooli);
				this.kayttajadao.lisaa(uusi);
				this.getIlmoitukset().informaatioAlertti(null, "alert.user.succesfulAdd");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			this.getIlmoitukset().informaatioAlertti(null, "alert.user.failedAdd");
		}
			
		tyhjennaTekstikentat();
	}
	
	/**
	 * Tyhjennetään tekstikentät.
	 */
	public void tyhjennaTekstikentat() {
		this.kayttajatunnusTextField.setText("");
		this.salasanaTextField.setText("");
		this.salasanaUudelleenTextField.setText("");
	}
	
	/**
	 * Tyhjennä napin painallus tyhjentää tekstikentät.
	 */
	@FXML
	private void tyhjennaButtonPainettu() {
		tyhjennaTekstikentat();
		this.rooliComboBox.getSelectionModel().select(this.getKaannokset().kaanna("button.choose"));
	}
	
	/**
	 * Poista napin painallus jolla poistetaan käyttäjä tietokannasta.
	 */
	@FXML
	private void poistaButtonPainettu() {
		if(this.getIlmoitukset().confirmaatioAlertti(null, "alert.user.deleteUser")) {
			this.getIlmoitukset().informaatioAlertti(null, "alert.user.succesfulRemove");		
		} else {
			this.getIlmoitukset().informaatioAlertti(null, "alert.user.failedRemove");	
		}
	}
	
}