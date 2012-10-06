/*
 * MyApplication.java
 *
 * Created on 5. Oktober 2012, 19:54
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
import com.example.vaadin.ui.HelpWindow;
//ClickListener
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class MyApplication extends Application implements ClickListener {
    /*
     * Variablen
     */
    private Button newContact = new Button("Kontakt hinzufügen");
    private Button search = new Button("Suche");
    private Button share = new Button("Teilen");
    private Button help = new Button("Hilfe");
    private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();
    private NavigationTree tree = new NavigationTree();
    private PersonContainer dataSource = PersonContainer.createWithTestData();
    /*
     * Im tut als Lazy bezeichnete Hilfen
     */
    private ListView listView = null;
    private PersonList personList = null;
    private PersonForm personForm = null;
    private SearchView searchView = null;
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
    }

    private void showSearchView() {
        setMainComponent(getSearchView());
    }
    
    private void showHelpWindow(){
        getMainWindow().addWindow(getHelpWindow());
    }
}