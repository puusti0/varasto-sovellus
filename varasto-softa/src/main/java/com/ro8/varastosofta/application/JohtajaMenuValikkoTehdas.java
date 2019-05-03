package com.ro8.varastosofta.application;

import com.ro8.varastosofta.application.model.JohtajaViewValikko;
import com.ro8.varastosofta.interfaces.INakymaController;
import com.ro8.varastosofta.interfaces.IMenuValikkoTehdas;
import com.ro8.varastosofta.interfaces.IViewValikko;

public class JohtajaMenuValikkoTehdas implements IMenuValikkoTehdas {

	@Override
	public IViewValikko luoViewValikko(INakymaController kontrolleri) {
		return new JohtajaViewValikko(kontrolleri);
	}

}
