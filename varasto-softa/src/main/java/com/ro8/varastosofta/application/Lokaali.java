package com.ro8.varastosofta.application;

import java.util.Locale;
import java.util.ResourceBundle;

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
	private static ResourceBundle bundle;

	public static void setLocale(Locale localeToSet) {
		locale = localeToSet;
		bundle = ResourceBundle.getBundle("MessagesBundle", localeToSet, new UTF8Control());
	}

	public static Locale getLocale() {
		return locale;
	}

	public static ResourceBundle getBundle() {
		return bundle;
	}
}
