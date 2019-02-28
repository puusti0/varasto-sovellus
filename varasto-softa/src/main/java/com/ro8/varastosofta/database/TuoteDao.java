package com.ro8.varastosofta.database;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.ro8.varastosofta.application.model.Tuote;

public class TuoteDao implements DAO {
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

	@Override
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
	
	@Override
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
	
	@Override
	public Tuote haeTuote(String nimi) {
		//TODO: Toteuta tuotteen haku Hibernatella.
		Tuote tuote = new Tuote(123, "Testi", 27);
		return tuote;
	}

}
