/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadin.ui;

/**
 *
 * @author Michael
 */
import com.vaadin.ui.Table;

public class PersonList extends Table {
     public PersonList() {
         // create some dummy data
         addContainerProperty("First Name", String.class, "Mark");
         addContainerProperty("Last Name", String.class, "Smith");
         addItem();
         addItem();
         setSizeFull();
     }    
}
