package com.ro8.varastosofta.interfaces;

import java.util.Locale;
import com.ro8.varastosofta.application.Istunto;

import javafx.scene.control.Menu;

/**
 * Rajapinta kontrollereille SessionManager:ia varten.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public interface INakymaController {
	void initSession(Istunto sessionManager, String sessionID);
	void setViewMenu(Menu menu);
}
