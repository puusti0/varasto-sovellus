package com.ro8.varastosofta.application.view;

import com.ro8.varastosofta.application.model.TuoteProp;
import com.ro8.varastosofta.application.model.Validaattori;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TuoteListausController {
	
	@FXML
	private TextField idTextField;
	@FXML
	private TextField nimiTextField;
	@FXML
	private TextField lkmTextField;
	
	//--------------------------------
	// Tässä blokissa koodia testausta varten voi poistaa jos ei tarvetta tai
	// muokata loppukäyttöä varten.
	
	@FXML
	private TableView<TuoteProp> tpData;
	@FXML
	private TableColumn<TuoteProp, String> tuoteNimi;
	@FXML 
	private TableColumn<TuoteProp, Integer> tuoteId;
	
	private ObservableList<TuoteProp> tuotteet =  FXCollections.observableArrayList();
	
	@FXML
	private void initialize() {
		// Yhdistetään sarakkeet niitä vastaaviin luokan tietoihin.
		this.tuoteNimi.setCellValueFactory(new PropertyValueFactory<>("Nimi"));
		this.tuoteId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		
		// Lisätään pari tuotetta testausta varten.
		this.tuotteet.add(new TuoteProp( 1, "Hila",10));
		this.tuotteet.add(new TuoteProp(2, "Vitkutin", 15));
		
		// Lisätään tuotteet ObservableListaan
		this.tpData.setItems(this.tuotteet);
		
		// Lisätää kuuntelija ja metodi tuotteiden tietojen näyttämistä varten listaan.
		this.tpData.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> naytaTuotteenTiedot(newValue));
	}
	
	//--------------------------------
	
	
	@FXML
	private void muokkaaNappiaPainettu() {
		
		if(Validaattori.onkoLisattavaTuoteValidi(this.idTextField.getText().toString(),
				this.nimiTextField.getText().toString(), this.lkmTextField.getText().toString())) {
			
			// Tähän tuotteen muokkaustoiminnallisuus
			//System.out.println("Nappia painettu");     <- testausta varten voi poistaa
			
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erhe Ilmoitus");
			alert.setHeaderText("Annetuissa tiedoissa virheitä");
			alert.setContentText("Tarkista, että yksikään kenttä ei ole tyhjä."
					+ "\nId-kentässä ja Lkm-kentässä on vain numeroita."
					+ "\nNimi-kentän teksti on korkeintaan 20 merkkiä pitkä");

			alert.showAndWait();
		}
		
	}
	
	private void naytaTuotteenTiedot(TuoteProp tuote) {
		
		this.idTextField.setText(tuote.getId() + "");
		this.nimiTextField.setText(tuote.getNimi() + "");
		this.lkmTextField.setText(tuote.getLkm() + "");
	}
	
}








































