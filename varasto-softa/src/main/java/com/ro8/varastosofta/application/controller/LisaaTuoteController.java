package com.ro8.varastosofta.application.controller;

import com.ro8.varastosofta.application.model.Ilmoitukset;
import com.ro8.varastosofta.application.model.Tooltipit;
import com.ro8.varastosofta.application.model.Tuote;
import com.ro8.varastosofta.application.model.Tuoteryhma;
import com.ro8.varastosofta.application.model.Validaattori;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.TuoteDao;
import com.ro8.varastosofta.database.TuoteryhmaDao;
import com.ro8.varastosofta.interfaces.IPopupController;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Kontrolleri tuotteen lisäämiseen.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class LisaaTuoteController extends Controller implements IPopupController {
	
	private Dao<Tuote, Integer> tuotedao;
	private Dao<Tuoteryhma, Integer> tuoteryhmadao;
	private List<Tuoteryhma> ryhmat;
	private HashMap<String, Integer> tuoteryhmat;
	
	@FXML
	private TextField idTextField;
	@FXML
	private TextField nimiTextField;
	@FXML
	private TextField lkmTextField;
	@FXML
	private TextField prizeTextField;
	@FXML
	private ComboBox<String> tuoteryhmaComboBox;
	@FXML 
	private Button lisaaButton;
	@FXML 
	private Button poistaButton;
	@FXML
	private Button tyhjennaButton;
	
	/**
	 * Tuotteen lisäys konstruktori.
	 */
	public LisaaTuoteController() {
		super();
		this.tuotedao = new TuoteDao();
		this.tuoteryhmadao = new TuoteryhmaDao();
		this.tuoteryhmat = new HashMap<>();
		try {
			this.ryhmat = this.tuoteryhmadao.listaa();
		} catch (SQLException e) {
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
	}
	
	/**
	 * Lisätään vihjeet komponentteihin.
	 */
	@Override
	public void lisaaVihjeetKomponentteihin() {
		this.getVihjeet().aseta(this.idTextField, "Set the ID for the product");
		this.getVihjeet().aseta(this.nimiTextField, "Set the name for the product.");
		this.getVihjeet().aseta(this.lkmTextField, "Set the quantity for the product.");
		this.getVihjeet().aseta(this.prizeTextField, "Set the prize for the product.");
		this.getVihjeet().aseta(this.tuoteryhmaComboBox, "Set the group for the product.");
		this.getVihjeet().aseta(this.lisaaButton, "Add the product to the database.");
		this.getVihjeet().aseta(this.poistaButton, "Remove the product from the database.");
		this.getVihjeet().aseta(this.tyhjennaButton, "Clear the input fields.");
	}
	
	
	/**
	 * Napin painallus lisää tuotteen tiedot tietokantaan mikäli annetut tiedot
	 * ovat oikein muutoin kehoittaa korjaamaan tiedot.
	 */
	@FXML
	private void lisaaButtonPainettu() {
		String id = this.idTextField.getText().toString();
		String nimi = this.nimiTextField.getText().toString();
		String lkm = this.lkmTextField.getText().toString();
		String hinta = this.prizeTextField.getText().toString();
		String valittuTuoteryhma = this.tuoteryhmaComboBox.getValue();
		if(this.getValidaattori().onkoLisattavaTuoteValidi(id, nimi, lkm) && this.getValidaattori().onkoTuoteryhmaValidi(valittuTuoteryhma)) {
			if(this.getIlmoitukset().confirmaatioAlertti(null, "alert.product.addProduct")) {
				try {
					Tuoteryhma tuoteryhma = this.tuoteryhmadao.hae(this.tuoteryhmat.get(valittuTuoteryhma));
					Tuote uusi = new Tuote(Integer.parseInt(id), nimi, Integer.parseInt(lkm),  Double.parseDouble(hinta),  tuoteryhma);
					this.tuotedao.lisaa(uusi);
					this.getIlmoitukset().informaatioAlertti(null, "alert.product.succesfulAdd");
				} catch (SQLException e1) {
					e1.printStackTrace();
					this.getIlmoitukset().informaatioAlertti(null, "alert.product.failedAdd");
				}
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(this.getKaannokset().kaanna("alert.product.addError.title"));
			alert.setHeaderText(null);
			alert.setContentText(this.getKaannokset().kaanna("alert.product.addError"));
			alert.showAndWait();
		}	
	}
	
	/**
	 * Napin painaminen validoi poistettavan tuotteen ID:n ja poistaa kyseisen 
	 * tuotteen tietokannasta sekä antaa ilmoituksen jos poisto onnistui tai ei.
	 */
	@FXML
	private void poistaTuoteButtonPainettu() {
		String id = this.idTextField.getText().toString();
		String numero = this.idTextField.getText().toString();
		
		if(this.getIlmoitukset().confirmaatioAlertti(null, "alert.product.deleteProduct")) {
			
			if(this.getValidaattori().onkoPoistettavaIdValidi(id) && this.getValidaattori().onkoNumero(numero)) {
				try {
					this.tuotedao.poista(Integer.parseInt(id));
					this.getIlmoitukset().informaatioAlertti(null, "alert.product.succesfulRemove");
				} catch (Exception e) {
					e.printStackTrace();
					this.getIlmoitukset().informaatioAlertti(null, "alert.product.failedRemove");
				}
			} else {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(this.getKaannokset().kaanna("alert.product.addError.title"));
				alert.setHeaderText(null);
				alert.setContentText(this.getKaannokset().kaanna("alert.product.deleteError"));

				alert.showAndWait();	
			}
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
		this.prizeTextField.setText("");
		this.tuoteryhmaComboBox.getSelectionModel().select("Choose");
	}

	@Override
	public void setObject(Object tuote) {
		Tuote tuoteX = (Tuote)tuote;
		this.idTextField.setText(tuoteX.getId() + "");
		this.nimiTextField.setText(tuoteX.getNimi());
		this.lkmTextField.setText(tuoteX.getLkm() + "");
		this.prizeTextField.setText(tuoteX.getHinta() + "");
		this.tuoteryhmaComboBox.getSelectionModel().select("Choose");			
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.ro8.varastosofta.application.IPopupController#asetaTeksti()
	 */
	public void asetaTeksti() {
		
		this.lisaaButton.setText("Update");
		
	}

}














