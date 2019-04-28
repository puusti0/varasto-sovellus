package com.ro8.varastosofta.application.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Yksilöllinen HBox, missä tekstikenttä ja nappi.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class HBoxWithButton extends HBox {
	
	private Label label = new Label();
    
	/**
	 * Konstruktori
	 * @param labelText tekstikentan teksti
	 * @param removebutton nappi
	 */
    public HBoxWithButton(String labelText, Button removebutton) {
    	super();
    	label.setText(labelText);
    	label.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(label, Priority.ALWAYS);
    	this.getChildren().addAll(label, removebutton);
    }
    
    
   public String getTeksti() {
	   return this.label.getText();
   }
}
