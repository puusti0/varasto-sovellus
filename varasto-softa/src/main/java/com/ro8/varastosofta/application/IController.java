package com.ro8.varastosofta.application;

import java.util.Locale;

/**
 * Rajapinta kontrollereille SessionManager:ia varten.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public interface IController {
	public void initSession(SessionManager sessionManager, String sessionID, Locale locale);
}
