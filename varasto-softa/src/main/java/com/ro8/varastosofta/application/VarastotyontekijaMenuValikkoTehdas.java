package com.ro8.varastosofta.application;

import com.ro8.varastosofta.application.controller.Controller;
import com.ro8.varastosofta.application.model.VarastotyontekijaViewValikko;
import com.ro8.varastosofta.interfaces.IMenuValikkoTehdas;
import com.ro8.varastosofta.interfaces.IViewValikko;

/**
 * Varastotyöntekijän valikkotehdas.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle.
 */
public class VarastotyontekijaMenuValikkoTehdas implements IMenuValikkoTehdas {

	/**
	 * Luodaan näkymävalikko
	 * @param valikkoa käyttävän näkymän kontrolleri
	 */
	@Override
	public IViewValikko luoViewValikko(Controller kontrolleri) {
		return new VarastotyontekijaViewValikko(kontrolleri);
	}

}
