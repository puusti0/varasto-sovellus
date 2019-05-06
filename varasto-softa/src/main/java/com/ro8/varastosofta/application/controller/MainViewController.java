package com.ro8.varastosofta.application.controller;

import com.ro8.varastosofta.application.Paaohjelma;
import com.ro8.varastosofta.application.Istunto;
import com.ro8.varastosofta.application.UTF8Control;
import com.ro8.varastosofta.application.model.Ilmoitukset;
import com.ro8.varastosofta.interfaces.INakymaController;
import com.ro8.varastosofta.interfaces.IController;
import com.ro8.varastosofta.interfaces.IMenuValikkoTehdas;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * Päänäkymän hallinnonti.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle.
 */
public class MainViewController implements INakymaController {
	
	private Istunto sessionManager;
	private IMenuValikkoTehdas valikkotehdas;
	private ResourceBundle kaannokset;
	private Ilmoitukset ilmoitukset;
	
	@FXML
	private BorderPane rootPane;
	@FXML
	private Menu viewMenu;
	@FXML
	private ImageView suomiImageView;
	@FXML
	private ImageView englishImageView;
	
	/**
	 * Päänäkymän kontrolleri.
	 * @param valikkotehdas nakymavalikon tehdas
	 */
	public MainViewController(IMenuValikkoTehdas valikkotehdas) {
		this.valikkotehdas = valikkotehdas;
		this.ilmoitukset = new Ilmoitukset();
	}
	
	/**
	 * Saadaan tehdas, jolla voidaan luoda nakymavalikko.
	 * @return nakymavalikon tehdas
	 */
	public IMenuValikkoTehdas getValikkoTehdas() {
		return valikkotehdas;
	}

	/**
	 * Asetetaan käyttäjän näkymävalikko.
	 * @param menu näkymävalikko
	 */
	public void setViewMenu(Menu menu) {
		this.viewMenu.getItems().addAll(menu.getItems());
	}

	/**
	 * Alustetaan istunto.
	 * @param istunto istunto
	 * @param istuntoID istunnon ID
	 */
	@Override
	public void initSession(Istunto istunto, String istuntoID) {
		this.suomiImageView.setImage(new Image("/Kuvat/Suomi.png"));
		this.englishImageView.setImage(new Image("/Kuvat/England.png"));
		this.sessionManager = istunto;
		this.kaannokset = ResourceBundle.getBundle("MessagesBundle", istunto.getKieli(), new UTF8Control());
		this.viewMenu.getItems().addAll(this.getValikkoTehdas().luoViewValikko(this, this.kaannokset).getMenu().getItems());
		aktivoiNakyma("TervehdysView.fxml");
	}
	
	/**
	 * Käsitellään valikon "Suomi"-valinta.
	 */
	@FXML
	protected void kasitteleSuomi() {
		this.sessionManager.valitseKieli("Suomi");
	}
	
	/**
	 * Käsitellään valikon "English"valinta.
	 */
	@FXML
	protected void kasitteleEnglish() {
		this.sessionManager.valitseKieli("English");
	}
	
	/**
	 * Käsitellään valikon "Kirjaudu ulos"-valinta.
	 */
	@FXML
	protected void kasitteleKirjauduUlos() {
		if(this.ilmoitukset.confirmaatioAlertti("Confirmation Dialog", null, kaannokset.getString("alert.logout"))) {
			this.sessionManager.kirjauduUlos();
		}
	}

	/**
	 * Käsitellään valikon "Lopeta"-valinta.
	 */
	@FXML
	private void kasitteleLopeta() {
		if(this.ilmoitukset.confirmaatioAlertti("Confirmation Dialog", null, kaannokset.getString("alert.exit"))) {
			this.sessionManager.lopeta();
		}
	}
	
	/**
	 * Asetetaan näkymä päänäkymän keskelle.
	 * @param view asetettavan näkymän nimi
	 */
	public void aktivoiNakyma(String view) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setResources(kaannokset);
			loader.setLocation(Paaohjelma.class.getResource("view/" + view));
			Parent nakyma = (Parent)loader.load();
			IController kontrolleri = loader.getController();
			kontrolleri.setKaannokset(this.kaannokset);
			kontrolleri.init();
			this.rootPane.setCenter(nakyma);	
		} catch(Exception e) {
			e.printStackTrace();
		}			
	}

}
