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

	/**
	 * Konsruktoru
	 * @param nimi paneelin nimi
	 */
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
		TableColumn<Tuote, Integer> tuoteId = new TableColumn<>("Id");
		TableColumn<Tuote, String> tuoteNimi = new TableColumn<>("Nimi");
		TableColumn<Tuote, Integer> tuoteLkm = new TableColumn<>("Lukumäärä");
		TableColumn<Tuote, Double> tuoteHinta = new TableColumn<>("Hinta");
		TableView<Tuote> tuotelistaus = new TableView<>();
		tuotelistaus.getColumns().addAll(tuoteId, tuoteNimi, tuoteLkm, tuoteHinta);
		
		//Yhdistetään sarakkeet niitä vastaaviin luokan tietoihin.
		tuoteId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tuoteNimi.setCellValueFactory(new PropertyValueFactory<>("nimi"));
		tuoteLkm.setCellValueFactory(new PropertyValueFactory<>("lkm"));
		tuoteHinta.setCellValueFactory(new PropertyValueFactory<>("hinta"));
		
		tuotelistaus.setItems(items);
		this.setContent(tuotelistaus);
		return tuotelistaus;
	}

}
