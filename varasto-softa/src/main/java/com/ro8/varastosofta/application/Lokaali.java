package com.ro8.varastosofta.application;

import java.util.Locale;

/**
 * Luokka lokaalin(locale) asetuksen säilyttämiseen ja asettamiseen/hakemiseen.
 * 
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 *
 */
public class Lokaali {
	
	private static Locale locale;
	
	public static void setLocale(Locale localeToSet) {	
		locale = localeToSet;	
	}
	
	public static Locale getLocale() {
		return locale;
	}
	
}
