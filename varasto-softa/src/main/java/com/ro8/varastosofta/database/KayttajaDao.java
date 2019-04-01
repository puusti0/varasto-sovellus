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

import com.ro8.varastosofta.application.model.Kayttaja;
import com.ro8.varastosofta.application.model.Tuote;

/**
 * Tietokantayhteys Kayttaja tauluun.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class KayttajaDao implements Dao<Kayttaja, Integer>{
	
	private SessionFactory istuntotehdas;
	private final StandardServiceRegistry rekisteri;
	
	/**
	 * Alustukset tietokanta yhteyttä varten.
	 */
	public KayttajaDao() {
		rekisteri = new StandardServiceRegistryBuilder().configure().build();
		try {
			istuntotehdas = new MetadataSources(rekisteri).buildMetadata().buildSessionFactory();
		}
		catch(Exception e){
			System.err.println("Istuntootehtaan luonti epäonnistui.");
			StandardServiceRegistryBuilder.destroy( rekisteri );
			e.printStackTrace();
		}
	}

	/**
	 * Lisätään kayttäjä tietokantaan.
	 * @param kayttaja lisättävä käyttäjä
	 */
	@Override
	public void lisaa(Kayttaja kayttaja) throws SQLException {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(kayttaja);
			transaktio.commit();
		}
		catch(Exception e){
			if (transaktio!=null) transaktio.rollback();
			System.err.println("lisaa(Kayttaja):");
			e.printStackTrace();
		}
		finally{
			istunto.close();
		}
		
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

	/**
	 * Tuotteiden listaaminen tietokannasta.
	 * @throws SQLException
	 * @return List<Kayttaja>
	 */
	@Override
	public List<Kayttaja> listaa() throws SQLException {
		List<Kayttaja> lista = new ArrayList<Kayttaja>();
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try {
			transaktio = istunto.beginTransaction();
			lista = istunto.createQuery( "FROM Kayttaja" ).list();
			transaktio.commit();
		} catch(Exception e) {
			if (transaktio!=null) transaktio.rollback();
			System.err.println("listaa(Kayttaja):");
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return lista;
	}

}
