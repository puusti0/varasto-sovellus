package com.ro8.varastosofta.application;

import com.ro8.varastosofta.application.model.JohtajaViewValikko;
import com.ro8.varastosofta.interfaces.INakymaController;
import com.ro8.varastosofta.interfaces.IMenuValikkoTehdas;
import com.ro8.varastosofta.interfaces.IViewValikko;

/**
 * Johtajan valikkotehdas.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle.
 */
public class JohtajaMenuValikkoTehdas implements IMenuValikkoTehdas {

	/**
	 * Luodaan näkymävalikko.
	 * @param kontrolleri valikkoa käyttävän näkymän kontrolleri
	 */
	@Override
	public IViewValikko luoViewValikko(INakymaController kontrolleri) {
		return new JohtajaViewValikko(kontrolleri);
	}

}
