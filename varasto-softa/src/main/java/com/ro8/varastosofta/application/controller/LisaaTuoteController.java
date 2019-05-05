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
	
	@FXML
	private TextField idTextField;
	@FXML
	private TextField nimiTextField;
	@FXML
	private TextField lkmTextField;
	@FXML
	private ComboBox<String> tuoteryhmaComboBox;
	@FXML 
	private Button lisaaButton;
	@FXML 
	private Button poistaButton;
	@FXML
	private Button tyhjennaButton;
		
	private Dao<Tuote, Integer> tuotedao;
	private Dao<Tuoteryhma, Integer> tuoteryhmadao;
	private List<Tuoteryhma> ryhmat;
	private HashMap<String, Integer> tuoteryhmat;
	private Ilmoitukset ilmoitukset;
	private ResourceBundle kaannokset;
	
	/**
	 * Tuotteen lisäys konstruktori.
	 */
	public LisaaTuoteController() {
		this.ilmoitukset = new Ilmoitukset();
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
		lisaaTooltipitKomponentteihin();
	}
	
	
	/**
	 * Napin painallus lisää tuotteen tiedot tietokantaan mikäli annetut tiedot
	 * ovat oikein muutoin kehoittaa korjaamaan tiedot.
	 */
	@FXML
	private void lisaaButtonPainettu() {
		if(Validaattori.onkoLisattavaTuoteValidi(this.idTextField.getText().toString(), this.nimiTextField.getText().toString(), this.lkmTextField.getText().toString())
				&& Validaattori.onkoTuoteryhmaValidi(this.tuoteryhmaComboBox.getValue())) {
			
			if(true) {
				try {
					Tuoteryhma tuoteryhma = this.tuoteryhmadao.hae(this.tuoteryhmat.get(this.tuoteryhmaComboBox.getValue()));
					Tuote uusi = new Tuote(Integer.parseInt(this.idTextField.getText().toString()), this.nimiTextField.getText().toString(), Integer.parseInt(this.lkmTextField.getText().toString()), tuoteryhma);
					this.tuotedao.lisaa(uusi);
					this.ilmoitukset.informaatioAlertti("Information Dialog", null, this.kaannokset.getString("alert.product.succesfulAdd"));
				} catch (SQLException e1) {
					e1.printStackTrace();
					this.ilmoitukset.informaatioAlertti("Information Dialog", null, this.kaannokset.getString("alert.product.failedAdd"));
				}
			}

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error alert");
			alert.setHeaderText(null);
			alert.setContentText("Please check that none of the fields are empty."
					+ "\nId-field and number-field contain only numbers."
					+ "\nName-fied can be at most 20 characters long.");

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
			
			if(Validaattori.onkoPoistettavaIdValidi(id) && Validaattori.onkoNumero(numero)) {
				try {
					this.tuotedao.poista(Integer.parseInt(id));
					this.ilmoitukset.informaatioAlertti("Information Dialog", null, this.kaannokset.getString("alert.product.succesfulRemove"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
					this.ilmoitukset.informaatioAlertti("Information Dialog", null, this.kaannokset.getString("alert.product.failedRemove"));
				} catch (SQLException e) {
					e.printStackTrace();
					this.ilmoitukset.informaatioAlertti("Information Dialog", null, this.kaannokset.getString("alert.product.failedRemove"));
				}
			} else {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Alert");
				alert.setHeaderText(null);
				alert.setContentText("Please, check that the id-field is not empty."
						+ "\nId-field contains only numbers.");

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
		this.tuoteryhmaComboBox.getSelectionModel().select("Choose");
	}

	@Override
	public void setObject(Object tuote) {
		Tuote tuoteX = (Tuote)tuote;
		this.idTextField.setText(tuoteX.getId() + "");
		this.nimiTextField.setText(tuoteX.getNimi());
		this.lkmTextField.setText(tuoteX.getLkm() + "");
		this.tuoteryhmaComboBox.getSelectionModel().select("Choose");			
	}
	
	/**
	 * Lisää Tooltipit komponentteihin.
	 */
	public void lisaaTooltipitKomponentteihin() {
		
		Tooltipit.asetaTooltip(this.idTextField, "Set the ID for the product");
		Tooltipit.asetaTooltip(this.nimiTextField, "Set the name for the product.");
		Tooltipit.asetaTooltip(this.lkmTextField, "Set the quantity for the product.");
		Tooltipit.asetaTooltip(this.tuoteryhmaComboBox, "Set the group for the product.");
		Tooltipit.asetaTooltip(this.lisaaButton, "Add the product to the database.");
		Tooltipit.asetaTooltip(this.poistaButton, "Remove the product from the database.");
		Tooltipit.asetaTooltip(this.tyhjennaButton, "Clear the input fields.");
		
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
}














