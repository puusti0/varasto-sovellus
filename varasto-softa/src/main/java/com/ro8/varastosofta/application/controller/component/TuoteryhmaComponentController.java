package com.ro8.varastosofta.application.controller.component;

import javafx.fxml.FXMLLoader;

public class TuoteryhmaComponentController {
	
	public TuoteryhmaComponentController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../view/component/TuoteryhmaComponent.fxml"));
		fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
	}

}
