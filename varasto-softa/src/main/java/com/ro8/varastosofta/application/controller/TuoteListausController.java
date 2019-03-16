package com.ro8.varastosofta.application.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.ro8.varastosofta.application.Main;
import com.ro8.varastosofta.application.Popup;
import com.ro8.varastosofta.application.model.Tuote;
import com.ro8.varastosofta.application.model.TuoteProp;
import com.ro8.varastosofta.application.model.Tuoteryhma;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.TuoteDao;
import com.ro8.varastosofta.database.TuoteryhmaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

/**
 * Käyttöliittymä tuotelistaukselle.
 * https://docs.oracle.com/javafx/2/ui_controls/accordion-titledpane.htm
 * https://docs.oracle.com/javafx/2/ui_controls/list-view.htm
 */
public class TuoteListausController {
	
	@FXML
	private Accordion tuotelistausAccordion;
	@FXML
	private Label idLabel;
	@FXML
	private Label nimiLabel;
	@FXML
	private Label lkmLabel;
	@FXML
	private Label tuoteryhmaLabel;
	
	private TuoteDao tuotedao;
	private Dao<Tuoteryhma, Integer> tuoteryhmadao;
	
	private List<Tuoteryhma> ryhmat;
	private HashMap<String, Tuoteryhma> tuoteryhmat;
	
	/**
	 * Tuotelistauksen kontrolleri.
	 */
	public TuoteListausController() {
		this.tuotedao = new TuoteDao();
		this.tuoteryhmadao = new TuoteryhmaDao();
		this.tuoteryhmat = new HashMap<String, Tuoteryhma>();
		try {
			this.ryhmat = this.tuoteryhmadao.listaa();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Tuoteryhma tuoteryhma : this.ryhmat) {
			this.tuoteryhmat.put(tuoteryhma.getNimi(), tuoteryhma);
		}
	}
	
	//--------------------------------
	// Tässä blokissa koodia testausta varten voi poistaa jos ei tarvetta tai
	// muokata loppukäyttöä varten.
	
	/**@FXML
	private TableView<TuoteProp> tpData;
	@FXML
	private TableColumn<TuoteProp, String> tuoteNimi;
	@FXML 
	private TableColumn<TuoteProp, Integer> tuoteId;
	@FXML 
	private TableColumn<TuoteProp, Integer> tuoteLkm;*/
	
	private ObservableList<TuoteProp> tuotteet =  FXCollections.observableArrayList();
	
	/**
	 * Alustetaan tuotteen tiedot JavaFX komponentteihin
	 */
	@FXML
	private void initialize() {
		List<TitledPane> listaus = new ArrayList<TitledPane>();
		for(Tuoteryhma tuoteryhma : this.ryhmat) {
			TitledPane tuoteryhmalista = new TitledPane();
			tuoteryhmalista.setText(tuoteryhma.getNimi());
			
			
			ListView<String> tuotelistaus = new ListView<String>();
			ObservableList<String> items =FXCollections.observableArrayList();
			try {
				List<Tuote> tuoteryhmanTuotteet = this.tuotedao.hae(tuoteryhma);
				for (Tuote tuote : tuoteryhmanTuotteet) {
					items.add(tuote.getId() + " " + tuote.getNimi() + " " + tuote.getLkm() + "kpl");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			tuotelistaus.setItems(items);
			tuoteryhmalista.setContent(tuotelistaus);
			listaus.add(tuoteryhmalista);
		}
		this.tuotelistausAccordion.getPanes().addAll(listaus);
		
		/** Yhdistetään sarakkeet niitä vastaaviin luokan tietoihin.
		this.tuoteNimi.setCellValueFactory(new PropertyValueFactory<>("nimi"));
		this.tuoteId.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.tuoteLkm.setCellValueFactory(new PropertyValueFactory<>("lkm"));*/
	}
		/**
		 // Lisätään pari tuotetta testausta varten.
		this.tuotteet.add(new TuoteProp( 1, "Hila",10));
		this.tuotteet.add(new TuoteProp(2, "Vitkutin", 15));
		*
		
		// Lisätään tuotteet ObservableListaan
		this.tpData.setItems(this.tuotteet);
		
		// Lisätää kuuntelija ja metodi tuotteiden tietojen näyttämistä varten listaan.
		this.tpData.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> naytaTuotteenTiedot(newValue));
		
		naytaTuotteenTiedot(this.tuotteet.get(0));
	}
	
	//--------------------------------
	
	/**
	 * Muokkaus napin toiminnallisuus, jolla avataan muokkaus popup
	 */
	@FXML
	private void handleMuokkaa() {		
		try {
			int id = Integer.parseInt(this.idLabel.getText());
			String nimi = this.nimiLabel.getText().toString();
			int lkm = Integer.parseInt(this.lkmLabel.getText());
			Tuote tuote = new Tuote(id, nimi, lkm);
			
			Popup muokkausPopup = new Popup("Muokkaa");
			muokkausPopup.open("MuokkausView.fxml", 300, 250, tuote);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Tuotelistauksen vähennä-napin toiminnalisuus.
	 */
	@FXML
	private void handleVahenna() {
		try {
			int id = Integer.parseInt(this.idLabel.getText());
			int lkm = Integer.parseInt(this.lkmLabel.getText()) - 1;
			int uusi = this.tuotedao.paivitaLukumaara(id, lkm);
			this.lkmLabel.setText(uusi+"");
			//this.tpData.getSelectionModel().selectedItemProperty().getValue().setLkm(uusi);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tuotelistauksen lisää-napin toiminnalisuus.
	 */
	@FXML
	private void handleLisaa() {
		try {
			int id = Integer.parseInt(this.idLabel.getText());
			int lkm = Integer.parseInt(this.lkmLabel.getText()) + 1;
			int uusi = this.tuotedao.paivitaLukumaara(id, lkm);
			this.lkmLabel.setText(uusi+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tuotteen tietojen lisääminen JavaFX-komponenteihin.
	 * @param tuote
	 */
	private void naytaTuotteenTiedot(TuoteProp tuote) {	
		this.idLabel.setText(tuote.getId() + "");
		this.nimiLabel.setText(tuote.getNimi() + "");
		this.lkmLabel.setText(tuote.getLkm() + "");
		this.tuoteryhmaLabel.setText(tuote.getTuoteryhma());
	}
	
}
