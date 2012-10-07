/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadin.ui;

/**
 *
 * @author Michael
 */
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.example.vaadin.MyApplication;
import com.example.vaadin.data.Person;
import com.example.vaadin.data.PersonContainer;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PersonForm extends Form implements ClickListener {
    private Button save = new Button("Speichern", (ClickListener) this);
    private Button cancel = new Button("Abbrechen", (ClickListener) this);
    private Button edit = new Button("Bearbeiten", (ClickListener) this);
    private MyApplication app;
    /* Für die neue Kontaktform */
    private boolean newContactMode = false;
    private Person newPerson = null;
    
    public PersonForm(MyApplication app) {
        this.app = app;
        // Enable buffering so that commit() must be called for the form
        // before input is written to the data. (Form input is not written
        // immediately through to the underlying object.)
        setWriteThrough(false);
        
        HorizontalLayout footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(save);
        footer.addComponent(cancel);
        footer.addComponent(edit);
        footer.setVisible(false);
        setFooter(footer);
    }
    
    @Override
    public void buttonClick(ClickEvent event){
        final Button source = event.getButton();
        if(source==save){
             /* If the given input is not valid there is no point in continuing */
            if(!isValid()){
                return;
            }
            commit();
            
            if(newContactMode){
                /* We need to add the new person to the container */
                Item addedItem = app.getDataSource().addItem(newPerson);
                /*
                 * We must update the form to use the Item from our datasource as we are now in edit mode
                 */
                setItemDataSource(addedItem);
                newContactMode = false;
            }
            setReadOnly(true);
        } else if(source==cancel){
            if (newContactMode) {
                newContactMode = false;
                setItemDataSource(null);
            } else {
                discard();
            }
            setReadOnly(true);
        } else if(source==edit){
            setReadOnly(false);
        }
    }
    
    @Override
    public void setItemDataSource(Item newDataSource){
        newContactMode = false;
        if(newDataSource!=null){
            List<Object> orderedProperties = Arrays
                    .asList(PersonContainer.NATURAL_COL_ORDER);
            super.setItemDataSource(newDataSource, orderedProperties);
            
            setReadOnly(true);
            getFooter().setVisible(true);
        } else {
            super.setItemDataSource(null);
            getFooter().setVisible(false);
        }
    }
    
    @Override
    public void setReadOnly(boolean readOnly){
        super.setReadOnly(readOnly);
        save.setVisible(!readOnly);
        cancel.setVisible(!readOnly);
        edit.setVisible(readOnly);
    }
    
    public void addContact(){
        //Create a temporary item for the form
        newPerson = new Person();
        setItemDataSource(new BeanItem(newPerson));
        newContactMode = true;
        setReadOnly(false);
    }
}
