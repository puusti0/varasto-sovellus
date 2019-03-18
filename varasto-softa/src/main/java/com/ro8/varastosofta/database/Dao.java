package com.ro8.varastosofta.database;

import java.sql.SQLException;
import java.util.List;

/**
 * CRUD-rajapinta
 * 
 * DAO Data Access Object
 * T olion tyyppi
 * K pääavaimen tyyppi
 * 
 */
public interface Dao<T, K> {
	
	/**
	 * CREATE
	 * Luodaan uusi olio tietokantaan.
	 * @param objekti
	 * @throws SQLException
	 */
    void lisaa(T objekti) throws SQLException;

    /**
     * Haetaan olion tiedot tietokannasta avaimen perusteella
     * @param avain
     * @return
     * @throws SQLException
     */
    T hae(K avain) throws SQLException;

    /**
     * UPDATE
     * Päivitetään olion tiedot tietokantaan
     * @param objekti
     * @return
     * @throws SQLException
     */
    T paivita(T objekti) throws SQLException;
    
    /**
     * DELETE
     * Poistetaan olio tietokannasta
     * @param avain
     * @throws SQLException
     */
    void poista(K avain) throws SQLException;

    /**
     * LIST
     * Haetaan kaikkien olioiden tiedot listaan tietokannasta
     * @return lista objekteista
     * @throws SQLException
     */
    List<T> listaa() throws SQLException;
}