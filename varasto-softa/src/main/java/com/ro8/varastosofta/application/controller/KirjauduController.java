package com.ro8.varastosofta.application.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.ro8.varastosofta.interfaces.INakymaController;
import com.ro8.varastosofta.application.Istunto;
import com.ro8.varastosofta.application.UTF8Control;
import com.ro8.varastosofta.application.model.Ilmoitukset;
import com.ro8.varastosofta.application.model.Kayttaja;
import com.ro8.varastosofta.application.model.Tooltipit;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.KayttajaDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;

/**
 * Kirjautumisivun kontrolleri.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle.
 */
public class KirjauduController implements INakymaController {
	
	private static int sessionID = 1;
	private Istunto istunto;
	private Dao<Kayttaja, Integer> kayttajadao;
	private Ilmoitukset ilmoitukset;
	private ResourceBundle kaannokset;
	
	@FXML
	private TextField tunnusTextField;
	@FXML
	private TextField salasanaTextField;
	@FXML
	private Button kirjauduButton;
	@FXML 
	private Button lopetaButton;
	@FXML
	private ImageView nimiImageView;
	
	public KirjauduController(Istunto istunto) {
		this.ilmoitukset = new Ilmoitukset();
		this.kayttajadao = new KayttajaDao();
		this.istunto = istunto;
	}
	
	/**
	 * Kirjautumissivun konstruktori.
	 */
	public KirjauduController() {
		
	}
	
	/**
	 * Alustetaan kirjautumissivun Javafx komponentit.
	 */
	@FXML public void initialize() {
		lisaaTooltipitKomponentteihin();
	}
	
	/**
	 * Ulos kirjautumisen käsitteleminen.
	 * @throws SQLException
	 */
	@FXML
	private void handleKirjaudu() throws SQLException {
		String sessionID = authorize();
        if (sessionID != null) {
        	this.istunto.setSessionID(sessionID);
          this.istunto.valitseNakyma();
        }
	}
	
	/**
	 * Lopetus napin painallus sulkee ohjelman.
	 */
	@FXML
	private void kasitteleLopeta() {
		if(this.ilmoitukset.confirmaatioAlertti("Confirmation Dialog", null, this.kaannokset.getString("alert.exit"))) {
			this.istunto.lopeta();
		}
		
	}
	
	/**
	 * Tarkistetaan salasana ja käyttäjätunnus yhdistelmä.
	 * TODO: tarkista tietokantataulun tietoja vasten.
	 * @throws SQLException
	 */
	private String authorize() throws SQLException {
		List<Kayttaja> kayttajat = this.kayttajadao.listaa();
		String sessionId = null;
		
		for (Kayttaja kayttaja : kayttajat) {
			if ((this.tunnusTextField.getText()).equals(kayttaja.getKayttajatunnus()) 
					&& (this.salasanaTextField.getText()).equals(kayttaja.getSalasana())) {
				sessionId = generateSessionID(kayttaja.getRooli().getNimi());
			} 
		}
		tarkistaSalasanaJaTunnus(sessionId);
		return sessionId;
	}
	
	/**
	 * Luodaan sessiolle yksilöllinen id
	 * @return session id
	 */
	private String generateSessionID(String rooli) {
		    sessionID++;
		    return rooli + "-" + sessionID;
	}
	
	/**
	 * Alustetaan kirjautumissivun sessio.
	 */
	@Override
	public void initSession(Istunto sessionManager, String sessionID) {
		this.kaannokset = ResourceBundle.getBundle("MessagesBundle", istunto.getKieli(), new UTF8Control());
		this.nimiImageView.setImage(new Image("/Kuvat/Nimi.PNG"));
	}
	
	/**
	 * Tarkistetaan ovatko annetut käyttäjätunnus ja salasana oikein
	 * jos ei niin annetaan ilmoitus.
	 * 
	 * @param sessionId, tarkistettava id jos ei null niin tunnus ja salasana oikein.
	 */
	public void tarkistaSalasanaJaTunnus(String sessionId) {
		
		if (sessionId == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error in Input");
			alert.setHeaderText(null);
			alert.setContentText("Please, check that the username and the password are correct.");
			alert.showAndWait();
		}
		
	}
	
	public void lisaaTooltipitKomponentteihin() {	
		Tooltipit.asetaTooltip(this.tunnusTextField, "Insert your username here, please.");
		Tooltipit.asetaTooltip(this.salasanaTextField, "Insert your password here, please");
		Tooltipit.asetaTooltip(this.kirjauduButton, "Press to log in.");
		Tooltipit.asetaTooltip(this.lopetaButton, "Press to exit the program");	
	}

	@Override
	public void setViewMenu(Menu menu) {
		
	}
	
}





















