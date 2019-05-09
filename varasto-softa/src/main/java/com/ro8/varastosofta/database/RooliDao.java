package com.ro8.varastosofta.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.ro8.varastosofta.application.model.Rooli;

/**
 * Tietokantayhteys Rooli tauluun.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class RooliDao implements Dao<Rooli, Integer>{
	
	private SessionFactory istuntotehdas;
	private final StandardServiceRegistry rekisteri;
	
	/**
	 * Alustukset tietokanta yhteyttä varten.
	 */
	public RooliDao() {
		rekisteri = new StandardServiceRegistryBuilder().configure().build();
		try {
			istuntotehdas = new MetadataSources(rekisteri).buildMetadata().buildSessionFactory();
		}
		catch(Exception e){
			StandardServiceRegistryBuilder.destroy( rekisteri );
			e.printStackTrace();
		}
	}

	/**
	 * Roolin lisääminen.
	 */
	@Override
	public void lisaa(Rooli objekti) throws SQLException {
		return;
	}

	/**
	 * Haetaan käyttäjän rooli tietokannasta.
	 * @param avain roolin yksilöivä tunnus
	 */
	@Override
	public Rooli hae(Integer avain) throws SQLException {
		Rooli rooli = new Rooli();
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try {
			transaktio = istunto.beginTransaction();
			istunto.load(rooli, avain);
			transaktio.commit();
		} catch(Exception e) {
			if (transaktio!=null) transaktio.rollback();
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return new Rooli(rooli.getId(), rooli.getNimi());
	}

	/**
	 * Roolin päivittäminen.
	 */
	@Override
	public Rooli paivita(Rooli objekti) throws SQLException {
		return null;
	}

	/**
	 * Roolin poistaminen.
	 */
	@Override
	public void poista(Integer avain) throws SQLException {
		return;
	}

	/**
	 * Haetaan kaikki roolit listaan tietokantaan.
	 * @return List<Rooli> lista rooleista.
	 */
	@Override
	public List<Rooli> listaa() throws SQLException {
		List<Rooli> lista = new ArrayList<Rooli>();
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try {
			transaktio = istunto.beginTransaction();
			lista = istunto.createQuery( "FROM Rooli" ).list();
			transaktio.commit();
		} catch(Exception e) {
			if (transaktio!=null) transaktio.rollback();
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return lista;
	}

}
