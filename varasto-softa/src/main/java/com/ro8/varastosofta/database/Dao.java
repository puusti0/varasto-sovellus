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
 * @author Riina Antikainen
 */
public interface Dao<T, K> {
	
	// CREATE
    void lisaa(T objekti) throws SQLException;

    // READ
    T hae(K avain) throws SQLException;

    // UPDATE
    T paivita(T objekti) throws SQLException;
    
    // DELETE
    void poista(K avain) throws SQLException;

    List<T> listaa() throws SQLException;
}