package com.ro8.varastosofta.application.controller;

import java.sql.SQLException;
import java.util.List;
import com.ro8.varastosofta.application.components.HBoxWithButton;
import com.ro8.varastosofta.application.model.Tuoteryhma;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.TuoteDao;
import com.ro8.varastosofta.database.TuoteryhmaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Tuoteryhmien hallinnointi.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class TuoteryhmatController {
	
	@FXML
	private Button lisaaButton;
	@FXML
	private ListView<HBoxWithButton> tuoteryhmaList;
	@FXML
	private TextField nimiTextField;
	
	private Dao<Tuoteryhma, Integer> tuoteryhmadao;
	private TuoteDao tuotedao;
	ObservableList<HBoxWithButton> listItems = FXCollections.observableArrayList();  
	
	public TuoteryhmatController() {
		this.tuoteryhmadao = new TuoteryhmaDao();
		this.tuotedao = new TuoteDao();
		
	}
	
	private Button luoPoistoNappi(String buttontext, Tuoteryhma tuoteryhma) {
		Button removebutton = new Button();
	
	removebutton.setText("Poista");
	removebutton.setOnAction(new EventHandler<ActionEvent>() {
	      @Override 
	      public void handle(ActionEvent event) {
	    	System.out.println(buttontext);
	        System.out.println(buttontext +  " valinta "+ tuoteryhma.getId());
	        try {
				tuotedao.poista(tuoteryhma);
				tuoteryhmadao.poista(tuoteryhma.getId());
				listItems.remove(tuoteryhma.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        /**if (selectedIdx != -1) {
	          String itemToRemove = tuoteryhmaList.getSelectionModel().getSelectedItem();
	          final int newSelectedIdx = (selectedIdx == tuoteryhmaList.getItems().size() - 1) ? selectedIdx - 1 : selectedIdx;
	          tuoteryhmaList.getItems().remove(selectedIdx);
	          tuoteryhmaList.getSelectionModel().select(newSelectedIdx);
	        }*/
	      }
	});
	return removebutton;
	}
	
	
	@FXML
	private void initialize() {
		try {
			List<Tuoteryhma> tuoteryhmat = tuoteryhmadao.listaa();
			for(Tuoteryhma tr : tuoteryhmat) {
				listItems.add(new HBoxWithButton(tr.toString(), luoPoistoNappi("Poistaa", tr)));
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
			
			listItems.add(new HBoxWithButton(tr.toString(), luoPoistoNappi("Poistaa", tr)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
