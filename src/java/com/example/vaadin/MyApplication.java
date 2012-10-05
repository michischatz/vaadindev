/*
 * MyApplication.java
 *
 * Created on 5. Oktober 2012, 19:54
 */
 
package com.example.vaadin;           

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MyApplication extends Application {
    @Override
    public void init() {
        setTheme("runo");
        buildMainLayout();
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
//        horizontalSplit.setSplitPosition(200, SplitPanel.UNITS_PIXELS);
        
        getMainWindow().setContent(layout);
    }
    
    public HorizontalLayout createToolbar() {
        HorizontalLayout lo = new HorizontalLayout();
        lo.addComponent(newContact);
        lo.addComponent(search);
        lo.addComponent(share);
        lo.addComponent(help);
        
        return lo;
    }
    
    private Button newContact = new Button("Kontakt hinzufügen");
    private Button search = new Button("Suche");
    private Button share = new Button("Teilen");
    private Button help = new Button("Hilfe");
    private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();
}