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
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

public class NavigationTree extends Tree {
    public static final Object SHOW_ALL = "Alle anzeigen";
    public static final Object SEARCH = "Suche";
    
    public NavigationTree(MyApplication app){
        addItem(SHOW_ALL);
        addItem(SEARCH);
        /*
         * We want items to be selectable but do not want the user to be able to de-select an item.
         */
        setSelectable(true);
        setNullSelectionAllowed(false);
        //Make application handle item click events
        addListener((ItemClickListener) app);
    }

    public class ListView extends VerticalSplitPanel {
        public ListView() {
            //
        }
    }
    
}
