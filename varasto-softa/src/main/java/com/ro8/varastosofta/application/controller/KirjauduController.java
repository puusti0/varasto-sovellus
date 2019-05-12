package com.ro8.varastosofta.application.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import com.ro8.varastosofta.application.Istunto;
import com.ro8.varastosofta.application.PasswordEncryptionService;
import com.ro8.varastosofta.application.model.Kayttaja;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.KayttajaDao;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Kirjautumisivun hallinnointi.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle.
 */
public class KirjauduController extends Controller {
	
	private static int sessionID = 1;
	private Dao<Kayttaja, Integer> kayttajadao;
	private Stage stage;
	
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
	
	/**
	 * Kirjautumisen konstruktori.
	 * @param stage näyttämö
	 */
	public KirjauduController(Stage stage) {
		super();
		this.stage = stage;
		this.kayttajadao = new KayttajaDao();
	}
	
	/**
	 * Alustetaan JavaFX komponentit.
	 */
	@FXML
    public void initialize() {
		this.nimiImageView.setImage(new Image("/Kuvat/Nimi.PNG"));
    }
	
	/**
	 * Lisätään vihjeet komponentteihin.
	 */
	@Override
	public void lisaaVihjeetKomponentteihin() {	
		this.getVihjeet().aseta(this.tunnusTextField, "Insert your username here, please.");
		this.getVihjeet().aseta(this.salasanaTextField, "Insert your password here, please");
		this.getVihjeet().aseta(this.kirjauduButton, "Press to log in.");
		this.getVihjeet().aseta(this.lopetaButton, "Press to exit the program");	
	}
	
	/**
	 * Sisäänkirjautuminen.
	 * @throws SQLException
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	@FXML
	private void kasitteleKirjaudu() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		String istuntoID = autentikoi();
		if (istuntoID == null) {
			this.getIlmoitukset().errorAlertti(null, "alert.user.failedLogon");
		} else {
			new Istunto(this.stage, istuntoID);
		}
	}
	
	/**
	 * Lopetus napin painallus sulkee ohjelman.
	 */
	@FXML
	private void kasitteleLopeta() {
		if(this.getIlmoitukset().confirmaatioAlertti(null, "alert.exit")) {
			Platform.exit();
		}
	}
	
	/**
	 * Tarkistetaan salasana ja käyttäjätunnus yhdistelmä.
	 * @throws SQLException
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	private String autentikoi() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		String sessionId = null;
		String tunnus = this.tunnusTextField.getText();
		String salasana = this.salasanaTextField.getText();
		if (this.getValidaattori().onkoSallittu(tunnus) && this.getValidaattori().onkoSallittu(salasana)) {
			List<Kayttaja> kayttajat = this.kayttajadao.listaa();
			for (Kayttaja kayttaja : kayttajat) {
				if (tunnus.equals(kayttaja.getKayttajatunnus()) && PasswordEncryptionService.authenticate(salasana, Base64.getDecoder().decode(kayttaja.getSalasana()), Base64.getDecoder().decode(kayttaja.getSuola()))) {
					sessionId = generateSessionID(kayttaja.getRooli().getNimi());
					break;
				} 
			}
		}
		return sessionId;
	}
	
	/**
	 * Luodaan sessiolle yksilöllinen id.
	 * @param käyttäjän rooli
	 * @return session id
	 */
	private static String generateSessionID(String rooli) {
		    sessionID++;
		    return rooli + "-" + sessionID;
	}
	
}
