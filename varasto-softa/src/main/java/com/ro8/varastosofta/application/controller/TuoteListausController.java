package com.ro8.varastosofta.application.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.ro8.varastosofta.application.Main;
import com.ro8.varastosofta.application.Popup;
import com.ro8.varastosofta.application.components.TitledPaneWithTableView;
import com.ro8.varastosofta.application.model.Tooltipit;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Käyttöliittymä tuotelistaukselle.
 * https://docs.oracle.com/javafx/2/ui_controls/accordion-titledpane.htm
 * https://docs.oracle.com/javafx/2/ui_controls/list-view.htm
 * https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
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
	@FXML
	private Button muokkaaButton;
	
	private TuoteDao tuotedao;
	private Dao<Tuoteryhma, Integer> tuoteryhmadao;
	
	private List<Tuoteryhma> ryhmat;
	private HashMap<String, Tuoteryhma> tuoteryhmat;
	
	/**
	 * Tuotelistauksen konstruktori.
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
		
		this.tuotelistausAccordion.getPanes().clear(); // Tyhjennetään Accordion aluksi.
				
		List<TitledPaneWithTableView> listaus = new ArrayList<TitledPaneWithTableView>();
		for(Tuoteryhma tuoteryhma : this.ryhmat) {
			ObservableList<Tuote> items = FXCollections.observableArrayList();
			// Haetaan tuoteryhmän tuotteet tietokannasta
			try {
				List<Tuote> tuoteryhmanTuotteet = this.tuotedao.hae(tuoteryhma);
				for (Tuote tuote : tuoteryhmanTuotteet) {
					items.add(tuote);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			TitledPaneWithTableView taulukko = new TitledPaneWithTableView(tuoteryhma.getNimi());
			TableView<Tuote> tuotelistaus = taulukko.luoTaulukko(items);
			tuotelistaus.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> naytaTuotteenTiedot(newValue));
			listaus.add(taulukko);
		}
		Tuoteryhma tyhja = null;
		TitledPaneWithTableView taulukko = new TitledPaneWithTableView("Muut");
		ObservableList<Tuote> items = FXCollections.observableArrayList();
		try {
			List<Tuote> tuoteryhmanTuotteet = this.tuotedao.hae(tyhja);
			for (Tuote tuote : tuoteryhmanTuotteet) {
				items.add(tuote);
			}
			TableView<Tuote> tuotelistaus = taulukko.luoTaulukko(items);
			tuotelistaus.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> naytaTuotteenTiedot(newValue));
			listaus.add(taulukko);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.tuotelistausAccordion.getPanes().addAll(listaus);
		
		lisaaTooltipitKomponentteihin();
	}

	
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
			muokkausPopup.open("LisaaTuoteView.fxml", 300, 250, tuote);
			
			// Alustetaan tuotelistaus uudestaan jotta muutokset näkyvät.
			initialize();
			
			// Tyhjennetään tuotteen tiedot näkymä.
			naytaTyhjatTiedot();
			

			
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
			
			// Alustetaan tuotelistaus uudestaan jotta muutokset näkyvät.
			initialize();
			
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
			
			// Alustetaan tuotelistaus uudestaan jotta muutokset näkyvät.
			initialize();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Tuotteen tietojen lisääminen JavaFX-komponenteihin.
	 * @param tuote
	 */
	private void naytaTuotteenTiedot(Tuote tuote) {	
		this.idLabel.setText(tuote.getId() + "");
		this.nimiLabel.setText(tuote.getNimi() + "");
		this.lkmLabel.setText(tuote.getLkm() + "");
		this.tuoteryhmaLabel.setText(tuote.getTuoteryhma().getNimi());
	}
	
	/**
	 * Asettaa tuotteen yksityiskohtaisen näkymän tyhjäksi.
	 */
	private void naytaTyhjatTiedot() {
		
		this.idLabel.setText("");
		this.nimiLabel.setText("");
		this.lkmLabel.setText("");
		this.tuoteryhmaLabel.setText("");
		
	}
	
	/**
	 * Lisää Tooltipit komponentteihin.
	 */
	public void lisaaTooltipitKomponentteihin() {
		
		Tooltipit.asetaTooltip(this.muokkaaButton, "Update the product information.");
		
	}
	
}










