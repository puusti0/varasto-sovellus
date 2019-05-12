package com.ro8.varastosofta.application.controller;

import com.ro8.varastosofta.application.Paaohjelma;
import com.ro8.varastosofta.application.Istunto;
import com.ro8.varastosofta.interfaces.IMenuValikkoTehdas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * Päänäkymän hallinnointi.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle.
 */
public class MainViewController extends Controller {
	
	private Istunto istunto;
	private IMenuValikkoTehdas valikkotehdas;
	
	@FXML
	private BorderPane rootPane;
	@FXML
	private Menu viewMenu;
	@FXML
	private ImageView suomiImageView;
	@FXML
	private ImageView englishImageView;
	@FXML
	private ImageView malaysiaImageView;
	
	/**
	 * Päänäkymän kontrolleri.
	 * @param valikkotehdas nakymavalikon tehdas
	 */
	public MainViewController(Istunto istunto, IMenuValikkoTehdas valikkotehdas) {
		super();
		this.istunto = istunto;
		this.valikkotehdas = valikkotehdas;
	}
	
	/**
	 * Alustetaan JavaFX komponentit.
	 */
	@FXML
    public void initialize() {
		this.suomiImageView.setImage(new Image("/Kuvat/Suomi.png"));
		this.englishImageView.setImage(new Image("/Kuvat/England.png"));
		this.malaysiaImageView.setImage(new Image("/Kuvat/Malaysia.png"));
		this.viewMenu.getItems().addAll(this.valikkotehdas.luoViewValikko(this).getMenu().getItems());
    }
	
	/**
	 * Lisätään vihjeet komponentteihin.
	 */
	@Override
	public void lisaaVihjeetKomponentteihin() {	
		return;
	}
	
	/**
	 * Käsitellään kielenvaihtaminen, kun painetaan Suomen lippua.
	 */
	@FXML
	protected void kasitteleSuomi() {
		this.istunto.valitseKieli("Suomi");
	}
	
	/**
	 * Käsitellään kielenvaihtaminen, kun painetaan Eglannin lippua.
	 */
	@FXML
	protected void kasitteleEnglish() {
		this.istunto.valitseKieli("English");
	}
	
	/**
	 *  Käsitellään kielenvaihtaminen, kun painetaan Malesian lippua.
	 */
	@FXML
	protected void kasitteleMalaysia() {
		this.istunto.valitseKieli("Malaysia");
	}
	
	/**
	 * Käsitellään valikon "Kirjaudu ulos"-valinta.
	 */
	@FXML
	protected void kasitteleKirjauduUlos() {
		if(this.getIlmoitukset().confirmaatioAlertti(null, "alert.logout")) {
			this.istunto.kirjauduUlos();
		}
	}

	/**
	 * Käsitellään valikon "Lopeta"-valinta.
	 */
	@FXML
	private void kasitteleLopeta() {
		if(this.getIlmoitukset().confirmaatioAlertti(null,"alert.exit")) {
			this.istunto.lopeta();
		}
	}
	
	/**
	 * Asetetaan näkymä päänäkymän keskelle.
	 * @param view asetettavan näkymän nimi
	 */
	public void aktivoiNakyma(String view) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setResources(this.getKaannokset().haeKielitiedosto());
			loader.setLocation(Paaohjelma.class.getResource("view/" + view));
			Parent nakyma = (Parent)loader.load();
			Controller kontrolleri = loader.getController();
			kontrolleri.lisaaVihjeetKomponentteihin();
			this.rootPane.setCenter(nakyma);	
		} catch(Exception e) {
			e.printStackTrace();
		}			
	}

}
