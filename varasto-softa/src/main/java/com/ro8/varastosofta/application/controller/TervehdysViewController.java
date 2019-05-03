package com.ro8.varastosofta.application.controller;

import java.util.ResourceBundle;
import com.ro8.varastosofta.interfaces.IController;

public class TervehdysViewController implements IController {
	
	private ResourceBundle kaannokset;

	@Override
	public void setKaannokset(ResourceBundle kaannokset) {
		this.kaannokset = kaannokset;
		
	}


}
