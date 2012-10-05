/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadin.ui;

/**
 *
 * @author Michael
 */

import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalSplitPanel;

public class NavigationTree extends Tree {
    public static final Object SHOW_ALL = "Alle anzeigen";
    public static final Object SEARCH = "Suche";
    
    public NavigationTree(){
        addItem(SHOW_ALL);
        addItem(SEARCH);
    }
    
    public class ListView extends VerticalSplitPanel {
        public ListView() {
            //
        }
    }
    
}
