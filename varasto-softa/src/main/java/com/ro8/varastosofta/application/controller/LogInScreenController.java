package com.ro8.varastosofta.application.controller;

import java.sql.SQLException;
import java.util.List;
import com.ro8.varastosofta.application.IController;
import com.ro8.varastosofta.application.SessionManager;
import com.ro8.varastosofta.application.model.Kayttaja;
import com.ro8.varastosofta.application.model.Rooli;
import com.ro8.varastosofta.database.Dao;
import com.ro8.varastosofta.database.KayttajaDao;
import com.ro8.varastosofta.database.RooliDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Kirjautumisivun kontrolleri.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle.
 */
public class LogInScreenController implements IController {
	
	@FXML
	private TextField tunnusTextField;
	@FXML
	private TextField salasanaTextField;
	@FXML
	private Button kirjauduButton;
	
	private static int sessionID = 1;
	private SessionManager sessionManager;
	private Dao<Rooli, Integer> roolidao;
	private Dao<Kayttaja, Integer> kayttajadao;
	
	public LogInScreenController() {
		this.kayttajadao = new KayttajaDao();
		this.roolidao = new RooliDao();
		
	}
	
	/**
	 * Tyhjennä napin painallus asettaa tunnus ja salasana kentät tyhjiksi.
	 * 
	 * @throws SQLException
	 */
	@FXML
	private void handleKirjaudu() throws SQLException {
		String sessionID = authorize();
        if (sessionID != null) {
          this.sessionManager.authenticated(sessionID);
        }
	}
	
	/**
	 * Tyhjennä napin painallus asettaa tunnus ja salasana kentät tyhjiksi.
	 */
	@FXML
	private void tyhjennaNappiaPainettu() {
		this.tunnusTextField.setText("");
		this.salasanaTextField.setText("");
	}
	
	/**
	 * Lopetus napin painallus sulkee ohjelman.
	 */
	@FXML
	private void kasitteleLopeta() {
		this.sessionManager.lopeta();
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
	public void initSession(SessionManager sessionManager, String sessionID) {
		this.sessionManager = sessionManager;
	}
	
}
