package com.ro8.varastosofta.application.controller;

import java.util.ResourceBundle;
import com.ro8.varastosofta.interfaces.IController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TervehdysViewController implements IController {
	
	private ResourceBundle kaannokset;
	
	@FXML
	private Label welcomeLabel;

	@Override
	public void setKaannokset(ResourceBundle kaannokset) {
		this.kaannokset = kaannokset;
	}

	
	public void init() {
		this.welcomeLabel.setText(this.kaannokset.getString("welcome.hello"));
	}

}
