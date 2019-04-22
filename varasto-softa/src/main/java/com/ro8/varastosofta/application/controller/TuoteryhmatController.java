package com.ro8.varastosofta.application.controller;

import java.sql.SQLException;
import java.util.List;

import com.ro8.varastosofta.application.model.Tuoteryhma;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.TuoteryhmaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TuoteryhmatController {
	
	@FXML
	private Button lisaaButton;
	@FXML
	private ListView<String> tuoteryhmaList;
	@FXML
	private TextField nimiTextField;
	
	private Dao tuoteryhmadao;
	ObservableList<String> listItems = FXCollections.observableArrayList();  
	
	public TuoteryhmatController() {
		this.tuoteryhmadao = new TuoteryhmaDao();
	}
	
	
	@FXML
	private void initialize() {
		try {
			List<Tuoteryhma> tuoteryhmat = tuoteryhmadao.listaa();
			for(Tuoteryhma tr : tuoteryhmat) {
				listItems.add(tr.toString());
			}
			tuoteryhmaList.setItems(listItems);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Tyhjentää tekstikentät.
	 */
	@FXML
	public void lisaaButton() {
		String tuoteryhma = this.nimiTextField.getText();
		Tuoteryhma tr = new Tuoteryhma();
		tr.setNimi(tuoteryhma);
		try {
			this.tuoteryhmadao.lisaa(tr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
