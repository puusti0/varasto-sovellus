package com.ro8.varastosofta.application.controller;

import java.sql.SQLException;
import java.util.List;

import com.ro8.varastosofta.application.Popup;
import com.ro8.varastosofta.application.model.Tuote;
import com.ro8.varastosofta.application.model.TuoteProp;
import com.ro8.varastosofta.application.model.Tuoteryhma;
import com.ro8.varastosofta.application.model.Validaattori;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.TuoteDao;
import com.ro8.varastosofta.database.TuoteryhmaDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TuoteListausController {
	
	@FXML
	private Label idLabel;
	@FXML
	private Label nimiLabel;
	@FXML
	private Label lkmLabel;
	@FXML
	private Label tuoteryhmaLabel;
	
	private Dao<Tuote, Integer> tuotedao;
	private Dao<Tuoteryhma, Integer> tuoteryhmadao;
	
	public  TuoteListausController() {
		tuotedao = new TuoteDao();
		tuoteryhmadao = new TuoteryhmaDao();
	}
	
	//--------------------------------
	// Tässä blokissa koodia testausta varten voi poistaa jos ei tarvetta tai
	// muokata loppukäyttöä varten.
	
	@FXML
	private TableView<TuoteProp> tpData;
	@FXML
	private TableColumn<TuoteProp, String> tuoteNimi;
	@FXML 
	private TableColumn<TuoteProp, Integer> tuoteId;
	@FXML 
	private TableColumn<TuoteProp, Integer> tuoteLkm;
	
	private ObservableList<TuoteProp> tuotteet =  FXCollections.observableArrayList();
	
	@FXML
	private void initialize() {
		// Yhdistetään sarakkeet niitä vastaaviin luokan tietoihin.
		this.tuoteNimi.setCellValueFactory(new PropertyValueFactory<>("nimi"));
		this.tuoteId.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.tuoteLkm.setCellValueFactory(new PropertyValueFactory<>("lkm"));
		
		List<Tuote> tietokantatuotteet;
		try {
			tietokantatuotteet = tuotedao.listaa();
			for (Tuote tuote : tietokantatuotteet) 
			{ 
				this.tuotteet.add(new TuoteProp( tuote.getId(), tuote.getNimi(), tuote.getLkm()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/**
		 // Lisätään pari tuotetta testausta varten.
		this.tuotteet.add(new TuoteProp( 1, "Hila",10));
		this.tuotteet.add(new TuoteProp(2, "Vitkutin", 15));
		*/
		
		// Lisätään tuotteet ObservableListaan
		this.tpData.setItems(this.tuotteet);
		
		// Lisätää kuuntelija ja metodi tuotteiden tietojen näyttämistä varten listaan.
		this.tpData.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> naytaTuotteenTiedot(newValue));
	}
	
	//--------------------------------
	
	
	@FXML
	private void muokkaa() {		
		
		
		if(Validaattori.onkoLisattavaTuoteValidi(this.idLabel.getText().toString(),
				this.nimiLabel.getText().toString(), this.lkmLabel.getText().toString())) {
			
			int id = Integer.parseInt(this.idLabel.getText());
			String nimi = this.nimiLabel.getText().toString();
			int lkm = Integer.parseInt(this.lkmLabel.getText());
			Tuote tuote = new Tuote(id, nimi, lkm);
			
			Popup muokkausPopup = new Popup("Muokkaa");
			muokkausPopup.open("MuokkausView.fxml", 300, 250, tuote);
		
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erhe Ilmoitus");
			alert.setHeaderText("Annetuissa tiedoissa virheitä");
			alert.setContentText("Tarkista, että yksikään kenttä ei ole tyhjä."
					+ "\nId-kentässä ja Lkm-kentässä on vain numeroita."
					+ "\nNimi-kentän teksti on korkeintaan 20 merkkiä pitkä"
					+ "\nTuoteryhmä-kentän teksti on korkeintaan 20 merkkiä pitkä");

			alert.showAndWait();
		}
	}
	
	private void naytaTuotteenTiedot(TuoteProp tuote) {	
		this.idLabel.setText(tuote.getId() + "");
		this.nimiLabel.setText(tuote.getNimi() + "");
		this.lkmLabel.setText(tuote.getLkm() + "");
	}
	
}








































