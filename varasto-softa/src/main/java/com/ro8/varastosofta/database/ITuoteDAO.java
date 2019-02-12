package com.ro8.varastosofta.database;

import com.ro8.varastosofta.application.model.Tuote;

public interface ITuoteDAO {
	public abstract boolean lisaaTuote(Tuote tuote);
	public abstract boolean poistaTuote(int id);
	public abstract Tuote haeTuote(String nimi);
}