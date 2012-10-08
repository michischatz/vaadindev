/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadin.ui;

/**
 *
 * @author Michael
 */
import com.example.vaadin.MyApplication;
import com.example.vaadin.data.PersonContainer;
import com.example.vaadin.data.SearchFilter;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class SearchView extends Panel {
    /*
     * Variablen
     */
    private TextField tf;
    private NativeSelect fieldToSearch;
    private CheckBox saveSearch;
    private TextField searchName;
    private MyApplication app;
    
    public SearchView(final MyApplication app) {
        this.app = app;
        
        setCaption("Kontakte suchen");
        setSizeFull();
        
        /* Use a FormLayout as main layout for this Panel */
        FormLayout formLayout = new FormLayout();
        setContent(formLayout);
        
        /* Create UI components */
        tf = new TextField("Suchbegriff");
        fieldToSearch = new NativeSelect("Feld zur suche");
        saveSearch = new CheckBox("Suche speichern");
        searchName = new TextField("Suche Name");
        Button search = new Button("Suchen");
        
        /* Initialize fieldToSearch */
        for (int i = 0; i < PersonContainer.NATURAL_COL_ORDER.length; i++) {
            fieldToSearch.addItem(PersonContainer.NATURAL_COL_ORDER[i]);
            fieldToSearch.setItemCaption(PersonContainer.NATURAL_COL_ORDER[i],PersonContainer.COL_HEADERS_GERMAN[i]);
        }
        fieldToSearch.setValue("lastName");
        fieldToSearch.setNullSelectionAllowed(false);
        
        /* Initialize save checkbox */
        saveSearch.setValue(true);
        saveSearch.setImmediate(true);
        saveSearch.addListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                searchName.setVisible(event.getButton().booleanValue());
            }
        });
        search.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                performSearch();
            }
        });
        
        /* Add all the created components to the form */
        addComponent(tf);
        addComponent(fieldToSearch);
        addComponent(saveSearch);
        addComponent(searchName);
        addComponent(search);
    }
    
    private void performSearch(){
        String searchTerm = (String) tf.getValue();
        SearchFilter searchFilter = new SearchFilter(fieldToSearch.getValue(),searchTerm, (String) searchName.getValue());
        if (saveSearch.booleanValue()) {
            app.saveSearch(searchFilter);
        }
        app.search(searchFilter);
    }
}
