/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadin.ui;

/**
 *
 * @author Michael
 */
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class PersonForm extends Form {
    private Button save = new Button("Speichern");
    private Button cancel = new Button("Abbrechen");
    
    public PersonForm() {
        addField("First Name", new TextField("First Name"));
        addField("Last Name", new TextField("Last Name"));
        HorizontalLayout footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(save);
        footer.addComponent(cancel);
        setFooter(footer);
    }
}
