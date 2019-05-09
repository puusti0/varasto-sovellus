package com.ro8.varastosofta.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.ro8.varastosofta.application.model.Tuote;
import com.ro8.varastosofta.application.model.Tuoteryhma;

/**
 * Tietokanta yhteys Tuote tauluun.
 */
public class TuoteDao implements Dao<Tuote, Integer> {
	
	private SessionFactory istuntotehdas;
	private final StandardServiceRegistry rekisteri;

	/**
	 * Alustukset tietokanta yhteyttä varten.
	 */
	public TuoteDao() {
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
	 * Lisää tuotteen tietokantaan
	 * @param tuote Tietokantaan lisättävä tuote
	 * @throws SQLException
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
			e.printStackTrace();
		}
		finally{
			istunto.close();
		}
	}

	/**
	 * Hae tuote tietyllä id:llä
	 * @param id haettavan tuotteen id
	 * @throws SQLException
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
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return new Tuote(tuote.getId(), tuote.getNimi(), tuote.getLkm());
	}

	/**
	 * Halutun tuotteen tietojen päivittäminen.
	 * @param id päivitettävän tuotteen id
	 * @throws SQLException
	 */
	@Override
	public Tuote paivita(Tuote tuote) throws SQLException {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try{
			transaktio = istunto.beginTransaction();
			Tuote tietokanta = istunto.get(Tuote.class, tuote.getId());
			if (tietokanta!= null){
				tietokanta.setTuoteryhma(tuote.getTuoteryhma());
				tietokanta.setNimi(tuote.getNimi());
				tietokanta.setLkm(tuote.getLkm());
				tietokanta.setHinta(tuote.getHinta());
			} else {
				return null;
			}
			transaktio.commit();
			return tietokanta;
		}
		catch(Exception e){
			if (transaktio!=null) transaktio.rollback();
			e.printStackTrace();
		}
		finally{
			istunto.close();
		}
		return null;
	}
	
	/**
	 * Tuotteen lukumäärän päivittäminen tietokantaan.
	 * @param id tuotteen id
	 * @param lkm tuotteen uusi lukumäärä
	 * @return tuotteen lukumäärä
	 * @throws SQLException
	 */
	public int paivitaLukumaara(int id, int lkm) throws SQLException {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try{
			transaktio = istunto.beginTransaction();
			Tuote tietokanta = istunto.get(Tuote.class, id);
			if (tietokanta!= null){
				tietokanta.setLkm(lkm);
			} else {
				return -1;
			}
			transaktio.commit();
			return lkm;
		}
		catch(Exception e){
			if (transaktio!=null) transaktio.rollback();
			e.printStackTrace();
		}
		finally{
			istunto.close();
		}
		return -1;
	}

	/**
	 * Tuotteen poistaminen tietokannasta.
	 * @param id poistettavan tuotteen id
	 * @throws SQLException
	 */
	@Override
	public void poista(Integer id) throws SQLException {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try{
			transaktio = istunto.beginTransaction();
			Tuote tuote = istunto.get(Tuote.class, id);
			if (tuote!= null){
				istunto.delete(tuote);
			}
			transaktio.commit();
		}
		catch(Exception e){
			if (transaktio!=null) transaktio.rollback();
			e.printStackTrace();
		}
		finally{
			istunto.close();
		}
	}

	/**
	 * Tuotteiden listaaminen tietokannasta.
	 * @throws SQLException
	 */
	@Override
	public List<Tuote> listaa() throws SQLException {
		List<Tuote> lista = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		try {
			transaktio = istunto.beginTransaction();
			lista = istunto.createQuery( "FROM Tuote" ).list();
			transaktio.commit();
		} catch(Exception e) {
			if (transaktio!=null) transaktio.rollback();
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return lista;
	}

	/**
	 * Hae kaikki tuotteet, jotka kuuluvat tiettyyn tuoteryhmään.
	 * @param tuoteryhma tuotteen tuoteryhma
	 * @throws SQLException
	 */
	public List<Tuote> hae(Tuoteryhma tuoteryhma) throws SQLException {
		List<Tuote> lista = new ArrayList<>();
		try (Session istunto = istuntotehdas.openSession()) {
			Transaction transaktio = istunto.beginTransaction();
			String sql;
			if (tuoteryhma == null) {
				sql = "FROM Tuote WHERE tuoteryhma_id = null";
			} else {
				sql = "FROM Tuote WHERE tuoteryhma_id = " + tuoteryhma.getId();
			}
			lista = istunto.createQuery(sql).list();
			transaktio.commit();
		} catch (Exception e) {
			
		}
		return lista;
	}
	
	/**
	 * Hae tuotteilta tuoteryhman, jotka kuuluvat tiettyyn tuoteryhmään.
	 * @param tuoteryhma tuotteen tuoteryhma
	 * @throws SQLException
	 */
	public void poista(Tuoteryhma tuoteryhma) throws SQLException {
		List<Tuote> lista = new ArrayList<>();
		Transaction transaktio = null;
		try (Session istunto = istuntotehdas.openSession()) {
			transaktio = istunto.beginTransaction();
			Query kysely = istunto.createQuery("UPDATE Tuote SET tuoteryhma_id = null WHERE tuoteryhma_id = " + tuoteryhma.getId());
			kysely.executeUpdate();
			transaktio.commit();
		} catch(Exception e) {
			if (transaktio != null) transaktio.rollback();
			e.printStackTrace();
		}
	}

}
