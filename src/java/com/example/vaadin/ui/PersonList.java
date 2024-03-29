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
import com.example.vaadin.data.Person;
import com.example.vaadin.data.PersonContainer;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;

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
         /* Collapsing und Reordern erlauben */
         setColumnCollapsingAllowed(true);
         setColumnReorderingAllowed(true);
         
         /*
          * Make table selectable, react immediatedly to user events, and pass events to the
          * controller (our main application)
          */
         setSelectable(true);
         setImmediate(true);
         addListener((ValueChangeListener) app);
         /*
          * We don't want to allow users to de-select a row 
          */
         setNullSelectionAllowed(false);
         
         // customize email column to have mailto: links using column generator
         addGeneratedColumn("email", new ColumnGenerator() {
             public Component generateCell(Table source, Object itemId, Object columnId) {
                 Person p = (Person) itemId;
                 Link l = new Link();
                 l.setResource(new ExternalResource("mailto:" + p.getEmail()));
                 l.setCaption(p.getEmail());
                 return l;
             }
         });
     }    
}
