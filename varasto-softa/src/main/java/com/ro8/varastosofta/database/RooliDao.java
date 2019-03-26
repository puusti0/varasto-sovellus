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
import com.ro8.varastosofta.application.model.Tuoteryhma;

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
		Rooli rooli = new Rooli();
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try {
			transaktio = istunto.beginTransaction();
			istunto.load(rooli, avain);
			transaktio.commit();
		} catch(Exception e) {
			if (transaktio!=null) transaktio.rollback();
			System.err.println("hae(Tuote):");
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return new Rooli(rooli.getId(), rooli.getNimi());
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
		List<Rooli> lista = new ArrayList<Rooli>();
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try {
			transaktio = istunto.beginTransaction();
			lista = istunto.createQuery( "FROM Rooli" ).list();
			transaktio.commit();
		} catch(Exception e) {
			if (transaktio!=null) transaktio.rollback();
			System.err.println("listaa(Rooli):");
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return lista;
	}

}
