package com.ro8.varastosofta.application.components;

import com.ro8.varastosofta.application.model.Tuote;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Yksilöllinen TitledPane, joka sisältää taulukon
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class TitledPaneWithTableView extends TitledPane {

	public TitledPaneWithTableView(String nimi) {
		super();
		this.setText(nimi);
	}
	
	/**
	 * Luodaan näkymän taulukko.
	 * @param items taulukon alkiot
	 * @return taulukko
	 */
	public TableView<Tuote> luoTaulukko(ObservableList<Tuote> items) {
		TableView<Tuote> tuotelistaus = new TableView<Tuote>();
		TableColumn<Tuote, Integer> tuoteId = new TableColumn<Tuote, Integer>("Id");
		TableColumn<Tuote, String> tuoteNimi = new TableColumn<Tuote, String>("Nimi");
		TableColumn<Tuote, Integer> tuoteLkm = new TableColumn<Tuote, Integer>("Lukumäärä");
		tuotelistaus.getColumns().addAll(tuoteId, tuoteNimi, tuoteLkm);
		
		//Yhdistetään sarakkeet niitä vastaaviin luokan tietoihin.
		tuoteId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tuoteNimi.setCellValueFactory(new PropertyValueFactory<>("nimi"));
		tuoteLkm.setCellValueFactory(new PropertyValueFactory<>("lkm"));
		
		tuotelistaus.setItems(items);
		this.setContent(tuotelistaus);
		
		return tuotelistaus;
	}

}
