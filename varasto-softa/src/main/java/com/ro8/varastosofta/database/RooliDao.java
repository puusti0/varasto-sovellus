package com.ro8.varastosofta.database;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
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
			System.err.println("Istuntootehtaan luonti epäonnistui.");
			StandardServiceRegistryBuilder.destroy( rekisteri );
			e.printStackTrace();
		}
	}

	@Override
	public void lisaa(Rooli objekti) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rooli hae(Integer avain) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rooli paivita(Rooli objekti) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void poista(Integer avain) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Rooli> listaa() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
