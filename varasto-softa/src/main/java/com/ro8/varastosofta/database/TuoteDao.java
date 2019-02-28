package com.ro8.varastosofta.database;

import java.sql.SQLException;
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

	
	public boolean lisaaTuote(Tuote tuote) {
		Tuote tuoteHibernate = new Tuote(tuote.getId(), tuote.getNimi(), tuote.getLkm());
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try{
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(tuoteHibernate);
			transaktio.commit();
			return true;
		}
		catch(Exception e){
			if (transaktio!=null) transaktio.rollback();
			System.err.println("createTuote:");
			e.printStackTrace();
		}
		finally{
			istunto.close();
		}
		return false;
	}
	

	public boolean poistaTuote(int id) {
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
			return true;
		}
		catch(Exception e){
			if (transaktio!=null) transaktio.rollback();
			System.err.println("createValuutta:");
			e.printStackTrace();
		}
		finally{
			istunto.close();
		}
		return false;
	}
	

	public Tuote haeTuote(String nimi) {
		//TODO: Toteuta tuotteen haku Hibernatella.
		Tuote tuote = new Tuote(123, "Testi", 27);
		return tuote;
	}

	@Override
	public void lisaa(Tuote objekti) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tuote hae(Integer avain) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tuote paivita(Tuote objekti) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void poista(Integer avain) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tuote> listaa() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
