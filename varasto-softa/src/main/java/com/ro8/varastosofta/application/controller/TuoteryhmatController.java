package com.ro8.varastosofta.application.controller;

import java.sql.SQLException;
import java.util.List;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.application.components.HBoxWithButton;
import com.ro8.varastosofta.application.model.Tuoteryhma;
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
public class TuoteryhmatController extends Controller {
	
	private Dao<Tuoteryhma, Integer> tuoteryhmadao;
	private TuoteDao tuotedao;
	ObservableList<HBoxWithButton> listItems = FXCollections.observableArrayList(); 
	
	@FXML
	private Button lisaaButton;
	@FXML
	private ListView<HBoxWithButton> tuoteryhmaList;
	@FXML
	private TextField nimiTextField;
	
	/**
	 * Tuoteryhmat kontrolleri.
	 */
	public TuoteryhmatController() {
		super();
		this.tuoteryhmadao = new TuoteryhmaDao();
		this.tuotedao = new TuoteDao();
	}
	
	/**
	 * Alustetaan näkymän JavaFX komponentit.
	 */
	@FXML
	private void initialize() {
		try {
			List<Tuoteryhma> tuoteryhmat = tuoteryhmadao.listaa();
			for(Tuoteryhma tr : tuoteryhmat) {
				listItems.add(new HBoxWithButton(tr.toString(), luoPoistoNappi(tr)));
			}
			tuoteryhmaList.setItems(listItems);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Lisätään vihjeet komponentteihin.
	 */
	@Override
	public void lisaaVihjeetKomponentteihin() {
		return;
	}
	
	/**
	 * Poisto-napin luominen.
	 * @param buttontext napin teksti
	 * @param tuoteryhma poistettava tuoteryhma
	 * @return nappi
	 */
	private Button luoPoistoNappi(Tuoteryhma tuoteryhma) {
		Button removebutton = new Button();
		removebutton.setText("Poista");
		removebutton.setOnAction(new EventHandler<ActionEvent>() {
	      @Override 
	      public void handle(ActionEvent event) {
	        try {
				tuotedao.poista(tuoteryhma);
				tuoteryhmadao.poista(tuoteryhma.getId());
				int index = tuoteryhmaList.getSelectionModel().getSelectedIndex();
				listItems.remove(index);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	      }
		});
		return removebutton;
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
			listItems.add(new HBoxWithButton(tr.toString(), luoPoistoNappi(tr)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
