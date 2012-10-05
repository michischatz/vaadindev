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
import com.example.vaadin.MyApplication;
import com.example.vaadin.data.PersonContainer;

public class PersonList extends Table {
    
    public PersonList(MyApplication app) {
         // create some dummy data
//         addContainerProperty("First Name", String.class, "Mark");
//         addContainerProperty("Last Name", String.class, "Smith");
//         addItem();
//         addItem();
         setSizeFull();
         //Daten aus dem Generator aus dem data-Package laden
         setContainerDataSource(app.getDataSource());
         setVisibleColumns(PersonContainer.NATURAL_COL_ORDER);
         setColumnHeaders(PersonContainer.COL_HEADERS_GERMAN);         
     }    
}
