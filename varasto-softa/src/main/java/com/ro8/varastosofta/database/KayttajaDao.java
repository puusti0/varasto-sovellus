package com.ro8.varastosofta.database;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.ro8.varastosofta.application.model.Kayttaja;

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
