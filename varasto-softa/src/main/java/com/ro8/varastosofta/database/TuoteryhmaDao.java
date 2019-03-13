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

import com.ro8.varastosofta.application.model.Tuote;
import com.ro8.varastosofta.application.model.Tuoteryhma;

public class TuoteryhmaDao implements Dao<Tuoteryhma, Integer> {
	
	private SessionFactory istuntotehdas;
	private final StandardServiceRegistry rekisteri;
	
	public TuoteryhmaDao() {
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
	public void lisaa(Tuoteryhma tuoteryhma) throws SQLException {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(tuoteryhma);
			transaktio.commit();
		}
		catch(Exception e){
			if (transaktio!=null) transaktio.rollback();
			System.err.println("lisaa(Tuoteryhma):");
			e.printStackTrace();
		}
		finally{
			istunto.close();
		}
	}

	@Override
	public Tuoteryhma hae(Integer avain) throws SQLException {
		Tuoteryhma tuoteryhma = new Tuoteryhma();
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try {
			transaktio = istunto.beginTransaction();
			istunto.load(tuoteryhma, avain);
			transaktio.commit();
		} catch(Exception e) {
			if (transaktio!=null) transaktio.rollback();
			System.err.println("hae(Tuote):");
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return new Tuoteryhma(tuoteryhma.getId(), tuoteryhma.getNimi());
	}

	@Override
	public Tuoteryhma paivita(Tuoteryhma objekti) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void poista(Integer avain) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Listaa tuoteryhmät tietokannasta
	 */
	@Override
	public List<Tuoteryhma> listaa() throws SQLException {
		List<Tuoteryhma> lista = new ArrayList<Tuoteryhma>();
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try {
			transaktio = istunto.beginTransaction();
			lista = (ArrayList<com.ro8.varastosofta.application.model.Tuoteryhma>)istunto.createQuery( "FROM Tuoteryhma" ).list();
			transaktio.commit();
		} catch(Exception e) {
			if (transaktio!=null) transaktio.rollback();
			System.err.println("listaa(Tuoteryhma):");
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return lista;
	}

}
