/*
 * MyApplication.java
 *
 * Created on 5. Oktober 2012, 19:54
 * 
 * @author Michael Schatz
 * @version 1.0
 */
 
package com.example.vaadin;           

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
//Weitere Componenten laden
import com.vaadin.ui.Component;
//SplitPanel laden
import com.vaadin.ui.SplitPanel;
//Eigenes Package (Navigationsbaum) importieren
import com.example.vaadin.ui.NavigationTree;
//ListView laden
import com.example.vaadin.ui.ListView;
//PersonenForm laden
import com.example.vaadin.ui.PersonForm;
//Personenlist laden
import com.example.vaadin.ui.PersonList;
//Datencontainer laden
import com.example.vaadin.data.PersonContainer;
//Views laden
import com.example.vaadin.ui.SearchView;
import com.example.vaadin.ui.SharingOptions;
import com.example.vaadin.ui.HelpWindow;
import com.sun.xml.internal.ws.api.PropertySet;
//ClickListener
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Field;
//Property
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
//valueChangex
import com.vaadin.data.Item;
//ImteClicks
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

public class MyApplication extends Application implements ClickListener, ValueChangeListener, ItemClickListener {
    /*
     * Variablen
     */
    private Button newContact = new Button("Kontakt hinzufügen");
    private Button search = new Button("Suche");
    private Button share = new Button("Teilen");
    private Button help = new Button("Hilfe");
    private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();
    private NavigationTree tree = new NavigationTree(this);
    private PersonContainer dataSource = PersonContainer.createWithTestData();
    /*
     * Im tut als Lazy bezeichnete Hilfen
     */
    private ListView listView = null;
    private PersonList personList = null;
    private PersonForm personForm = null;
    private SearchView searchView = null;
    private SharingOptions sharingOptions = null;
    private HelpWindow helpWindow = null;
    
    /*
     * Methoden
     */
    @Override
    public void init() {
        setTheme("runo");
        buildMainLayout();
        setMainComponent(getListView());
    }
    
    private void buildMainLayout(){
        setMainWindow(new Window("Adressbuch Demo Programm"));
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        
        layout.addComponent(createToolbar());
        layout.addComponent(horizontalSplit);
        
        /* Allocate all available extra space to the horizontal split panel */
        layout.setExpandRatio(horizontalSplit, 1);
        /* Set the initial split position so we can have a 200 pixel menu to the left */
        horizontalSplit.setSplitPosition(200, SplitPanel.UNITS_PIXELS); //Funktioniert nicht
        /* Navigationstree hinzufügen*/
        horizontalSplit.setFirstComponent(tree);
        
        getMainWindow().setContent(layout);
    }
    
    public HorizontalLayout createToolbar() {
        HorizontalLayout lo = new HorizontalLayout();
        //Componenten laden
        lo.addComponent(newContact);
        lo.addComponent(search);
        lo.addComponent(share);
        lo.addComponent(help);
        
        search.addListener((Button.ClickListener) this);
        help.addListener((ClickListener) this);
        share.addListener((ClickListener) this);
        
        return lo;
    }
    
    private void setMainComponent(Component c) {
        horizontalSplit.setSecondComponent(c);
    }
       
    private ListView getListView() {
        if(listView == null){
            personList = new PersonList(this); //this ist erst in einem späterem Kapitel (iwo bei 4) notwendig!!
            personForm = new PersonForm();
            listView = new ListView(personList, personForm);
        }
        return listView;
    }
    public PersonContainer getDataSource() {
        return dataSource;
    }  
    
    private SearchView getSearchView(){
        if(searchView == null){
            searchView = new SearchView(this);
        }
        return searchView;
    }
    
    public SharingOptions getSharingOptions() {
        if(sharingOptions == null){
            sharingOptions = new SharingOptions();
        }
        return sharingOptions;
    }
    
    private HelpWindow getHelpWindow(){
        if(helpWindow == null){
            helpWindow = new HelpWindow();
        }
        return helpWindow;
    }

    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == search) {
            showSearchView();
        }
        if (source == help){
            showHelpWindow();
        }
        if(source == share){
            showShareWindow();
        }
    }

    private void showSearchView() {
        setMainComponent(getSearchView());
    }
    
    private void showHelpWindow(){
        getMainWindow().addWindow(getHelpWindow());
    }
    
    private void showShareWindow(){
        getMainWindow().addWindow(getSharingOptions());
    }
    
    private void showListView() {
        setMainComponent(getListView());
    }
    
    public void valueChange(ValueChangeEvent event){
        Property property = event.getProperty();
        if (property == personList) {
            Item item = personList.getItem(personList.getValue());
            if (item != personForm.getItemDataSource()) {
                personForm.setItemDataSource(item);
            }
        }
    }
    
    public void itemClick(ItemClickEvent event) {
        if (event.getSource() == tree) {
            Object itemId = event.getItemId();
            if (itemId != null) {
                if (NavigationTree.SHOW_ALL.equals(itemId)) {
                    showListView();
                }
            } else if (itemId == NavigationTree.SEARCH) {
                showSearchView();
            } 
        }
    }
}