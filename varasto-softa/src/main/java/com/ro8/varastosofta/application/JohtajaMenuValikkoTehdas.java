package com.ro8.varastosofta.application;

import java.util.ResourceBundle;
import com.ro8.varastosofta.application.controller.Controller;
import com.ro8.varastosofta.application.model.JohtajaViewValikko;
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
	 * @param kaannokset resurssipaketti käännksille
	 */
	@Override
	public IViewValikko luoViewValikko(Controller kontrolleri) {
		return new JohtajaViewValikko(kontrolleri);
	}

}
