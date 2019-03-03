package com.ro8.varastosofta.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.ro8.varastosofta.application.model.Tuote;

public class TuoteDao implements Dao<Tuote, Integer> {
	
	private SessionFactory istuntotehdas;
	private final StandardServiceRegistry rekisteri;

	public TuoteDao() {
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
	 * 
	 */
	@Override
	public void lisaa(Tuote tuote) throws SQLException {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(tuote);
			transaktio.commit();
		}
		catch(Exception e){
			if (transaktio!=null) transaktio.rollback();
			System.err.println("lisaa(Tuote):");
			e.printStackTrace();
		}
		finally{
			istunto.close();
		}
	}

	/**
	 * 
	 */
	@Override
	public Tuote hae(Integer avain) throws SQLException {
		Tuote tuote = new Tuote();
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try {
			transaktio = istunto.beginTransaction();
			istunto.load(tuote, avain);
			transaktio.commit();
		} catch(Exception e) {
			if (transaktio!=null) transaktio.rollback();
			System.err.println("hae(Tuote):");
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return new Tuote(tuote.getId(), tuote.getNimi(), tuote.getLkm());
	}

	/**
	 * 
	 */
	@Override
	public Tuote paivita(Tuote tuote) throws SQLException {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try{
			transaktio = istunto.beginTransaction();
			Tuote tietokanta = (Tuote)istunto.get(Tuote.class, tuote.getId());
			if (tietokanta!= null){
				tietokanta.setTuoteryhma(tuote.getTuoteryhma());
				tietokanta.setNimi(tuote.getNimi());
				tietokanta.setLkm(tuote.getLkm());
				tietokanta.setHinta(tuote.getHinta());
			}
			else{
				System.out.println("Ei löytynyt päivitettävää!");
				return null;
			}
			transaktio.commit();
			return tietokanta;
		}
		catch(Exception e){
			if (transaktio!=null) transaktio.rollback();
			System.err.println("paivita(Tuote):");
			e.printStackTrace();
		}
		finally{
			istunto.close();
		}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public void poista(Integer id) throws SQLException {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try{
			transaktio = istunto.beginTransaction();
			Tuote tuote = (Tuote)istunto.get(Tuote.class, id);
			if (tuote!= null){
				istunto.delete(tuote);
				System.out.println("Tuote id:llä " + id + " poistettu!");
			}
			else{
				System.out.println("Ei löytynyt poistettavaa!");
			}
			transaktio.commit();
		}
		catch(Exception e){
			if (transaktio!=null) transaktio.rollback();
			System.err.println("createValuutta:");
			e.printStackTrace();
		}
		finally{
			istunto.close();
		}
	}

	/**
	 * 
	 */
	@Override
	public List<Tuote> listaa() throws SQLException {
		List<Tuote> lista = new ArrayList<Tuote>();
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try {
			transaktio = istunto.beginTransaction();
			lista = (ArrayList<com.ro8.varastosofta.application.model.Tuote>)istunto.createQuery( "FROM Tuote" ).list();
			transaktio.commit();
		} catch(Exception e) {
			if (transaktio!=null) transaktio.rollback();
			System.err.println("listaa(Tuote):");
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return lista;
	}

}
