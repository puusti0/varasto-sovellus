package com.ro8.varastosofta.application.controller;

import java.sql.SQLException;

import com.ro8.varastosofta.application.IController;
import com.ro8.varastosofta.application.model.Tuote;
import com.ro8.varastosofta.application.model.Tuoteryhma;
import com.ro8.varastosofta.application.model.Validaattori;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.TuoteDao;
import com.ro8.varastosofta.database.TuoteryhmaDao;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class MuokkausController implements IController {
	
	@FXML
	private TextField idTextField;
	@FXML
	private TextField nimiTextField;
	@FXML
	private TextField lkmTextField;
	@FXML
	private TextField tuoteryhmaTextField;
	
	private Tuote tuote;
	
	private Dao<Tuote, Integer> tuotedao;
	private Dao<Tuoteryhma, Integer> tuoteryhmadao;
	
	public MuokkausController() {
		this.tuotedao = new TuoteDao();
		this.tuoteryhmadao = new TuoteryhmaDao();
	}
	
	
	@FXML
	private void tallenna() {
		if(Validaattori.onkoLisattavaTuoteValidi(this.idTextField.getText().toString(),
				this.nimiTextField.getText().toString(), this.lkmTextField.getText().toString())) {
			
			try {
				int id = Integer.parseInt(this.idTextField.getText());
				String nimi = this.nimiTextField.getText().toString();
				int lkm = Integer.parseInt(this.lkmTextField.getText());
				Tuote tuote = new Tuote(id, nimi, lkm);
				this.tuotedao.paivita(tuote);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			
			// Tähän tuotteen muokkaustoiminnallisuus
			//System.out.println("Nappia painettu");     <- testausta varten voi poistaa
			
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


	@Override
	public void setObject(Object tuote) {
		this.tuote = (Tuote)tuote;
		this.idTextField.setText(this.tuote.getId() + "");
		this.nimiTextField.setText(this.tuote.getNimi());
		this.lkmTextField.setText(this.tuote.getLkm() + "");
	}

}
