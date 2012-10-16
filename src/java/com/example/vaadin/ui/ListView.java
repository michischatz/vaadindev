/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadin.ui;

/**
 *
 * @author Michael
 */
import com.vaadin.ui.VerticalSplitPanel;


public class ListView extends VerticalSplitPanel {
    public ListView(PersonList personList, PersonForm personForm) {
        /* Style hinzufügen */
        addStyleName("view");
        setFirstComponent(personList);
        setSecondComponent(personForm);
        setSplitPosition(40);
    }    
}
