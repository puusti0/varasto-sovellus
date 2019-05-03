package com.ro8.varastosofta.application;

import com.ro8.varastosofta.application.model.VarastotyontekijaViewValikko;
import com.ro8.varastosofta.interfaces.INakymaController;
import com.ro8.varastosofta.interfaces.IMenuValikkoTehdas;
import com.ro8.varastosofta.interfaces.IViewValikko;

public class VarastotyontekijaMenuValikkoTehdas implements IMenuValikkoTehdas {

	@Override
	public IViewValikko luoViewValikko(INakymaController kontrolleri) {
		return new VarastotyontekijaViewValikko(kontrolleri);
	}

}
