package com.ro8.varastosofta.application;

import com.ro8.varastosofta.application.controller.LogInScreenController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class SessionManager {
	
	private Scene scene;
	
	/**
	 * 
	 * @param scene
	 */
	public SessionManager(Scene scene) {
		this.scene = scene;
	}
	
	/**
	 * 
	 * @param sessionID
	 */
	public void authenticated(String sessionID) {
		naytaNakyma(sessionID, "MainView.fxml");
	}
	
	/**
	 * 
	 */
	public void logout() {
	    naytaLogInScreen();
	}
	
	/**
	 * 
	 */
	public void naytaLogInScreen() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LogInScreen.fxml"));
			final Pane login = (Pane)loader.load();
			this.scene.setRoot(login);
			final LogInScreenController controller = (LogInScreenController)loader.getController();
	        controller.initSessionManager(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Näytetään session näkymä
	 */
	public void naytaNakyma(String sessionID, String view) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/" + view));
			Parent nakyma = (Parent)loader.load();
			this.scene.setRoot(nakyma);
			IController controller = (IController)loader.getController();
			controller.initSession(this, sessionID);
		} catch(Exception e) {
			e.printStackTrace();
		}			
	}
}
