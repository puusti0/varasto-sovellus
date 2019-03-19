package com.ro8.varastosofta.database;

import java.sql.SQLException;
import java.util.List;
import com.ro8.varastosofta.application.model.Kayttaja;

/**
 * Tietokantayhteys Kayttaja tauluun.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class KayttajaDao implements Dao<Kayttaja, Integer>{

	@Override
	public void lisaa(Kayttaja objekti) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Kayttaja hae(Integer avain) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kayttaja paivita(Kayttaja objekti) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void poista(Integer avain) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Kayttaja> listaa() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
