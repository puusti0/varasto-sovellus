package com.ro8.varastosofta.application.controller;

import com.ro8.varastosofta.application.model.Ilmoitukset;
import com.ro8.varastosofta.application.model.Tooltipit;
import com.ro8.varastosofta.application.model.Tuote;
import com.ro8.varastosofta.application.model.Tuoteryhma;
import com.ro8.varastosofta.application.model.Validaattori;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.TuoteDao;
import com.ro8.varastosofta.database.TuoteryhmaDao;
import com.ro8.varastosofta.interfaces.IController;
import com.ro8.varastosofta.interfaces.IPopupController;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
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
public class LisaaTuoteController implements IPopupController, IController {
	
	private Dao<Tuote, Integer> tuotedao;
	private Dao<Tuoteryhma, Integer> tuoteryhmadao;
	private List<Tuoteryhma> ryhmat;
	private HashMap<String, Integer> tuoteryhmat;
	private Ilmoitukset ilmoitukset;
	private ResourceBundle kaannokset;
	private Validaattori validaattori;
	private Tooltipit tooltipit;
	
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
		this.tooltipit = new Tooltipit();
		this.ilmoitukset = new Ilmoitukset();
		this.tuotedao = new TuoteDao();
		this.tuoteryhmadao = new TuoteryhmaDao();
		this.tuoteryhmat = new HashMap<>();
		validaattori = new Validaattori();
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
		lisaaTooltipitKomponentteihin();
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
		if(validaattori.onkoLisattavaTuoteValidi(id, nimi, lkm) && validaattori.onkoTuoteryhmaValidi(valittuTuoteryhma)) {
			if(this.ilmoitukset.confirmaatioAlertti("Confirmation Dialog", null, this.kaannokset.getString("alert.product.addProduct"))) {
				try {
					Tuoteryhma tuoteryhma = this.tuoteryhmadao.hae(this.tuoteryhmat.get(valittuTuoteryhma));
					Tuote uusi = new Tuote(Integer.parseInt(id), nimi, Integer.parseInt(lkm),  Double.parseDouble(hinta),  tuoteryhma);
					this.tuotedao.lisaa(uusi);
					this.ilmoitukset.informaatioAlertti("Information Dialog", null, this.kaannokset.getString("alert.product.succesfulAdd"));
				} catch (SQLException e1) {
					e1.printStackTrace();
					this.ilmoitukset.informaatioAlertti("Information Dialog", null, this.kaannokset.getString("alert.product.failedAdd"));
				}
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(this.kaannokset.getString("alert.product.addError.title"));
			alert.setHeaderText(null);
			alert.setContentText(this.kaannokset.getString("alert.product.addError"));

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
		
		if(this.ilmoitukset.confirmaatioAlertti("Confirmation Dialog", null, this.kaannokset.getString("alert.product.deleteProduct"))) {
			
			if(validaattori.onkoPoistettavaIdValidi(id) && validaattori.onkoNumero(numero)) {
				try {
					this.tuotedao.poista(Integer.parseInt(id));
					this.ilmoitukset.informaatioAlertti("Information Dialog", null, this.kaannokset.getString("alert.product.succesfulRemove"));
				} catch (Exception e) {
					e.printStackTrace();
					this.ilmoitukset.informaatioAlertti("Information Dialog", null, this.kaannokset.getString("alert.product.failedRemove"));
				}
			} else {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(this.kaannokset.getString("alert.product.addError.title"));
				alert.setHeaderText(null);
				alert.setContentText(this.kaannokset.getString("alert.product.deleteError"));

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
	
	/**
	 * Lisää Tooltipit komponentteihin.
	 */
	public void lisaaTooltipitKomponentteihin() {
		tooltipit.asetaTooltip(this.idTextField, "Set the ID for the product");
		tooltipit.asetaTooltip(this.nimiTextField, "Set the name for the product.");
		tooltipit.asetaTooltip(this.lkmTextField, "Set the quantity for the product.");
		tooltipit.asetaTooltip(this.prizeTextField, "Set the prize for the product.");
		tooltipit.asetaTooltip(this.tuoteryhmaComboBox, "Set the group for the product.");
		tooltipit.asetaTooltip(this.lisaaButton, "Add the product to the database.");
		tooltipit.asetaTooltip(this.poistaButton, "Remove the product from the database.");
		tooltipit.asetaTooltip(this.tyhjennaButton, "Clear the input fields.");
	}
	
	/* (non-Javadoc)
	 * @see com.ro8.varastosofta.application.IPopupController#asetaTeksti()
	 */
	public void asetaTeksti() {
		
		this.lisaaButton.setText("Update");
		
	}

	@Override
	public void setKaannokset(ResourceBundle kaannokset) {
		this.kaannokset = kaannokset;
	}

	@Override
	public void init() {
		return;
	}
}














