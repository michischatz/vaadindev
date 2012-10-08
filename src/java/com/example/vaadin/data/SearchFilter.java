/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadin.data;

/**
 *
 * @author Michael
 */
import java.io.Serializable;

public class SearchFilter implements Serializable {
    /*
     * Variablen
     */
    private final String term;
    private final Object propertyId;
    private String searchName;
    

    public SearchFilter(Object propertyId, String searchTerm, String name) {
        this.propertyId = propertyId;
        this.term = searchTerm;
        this.searchName = name;
    }
    
    @Override
    public String toString(){
        return getSearchName();
    }

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @return the propertyId
     */
    public Object getPropertyId() {
        return propertyId;
    }

    /**
     * @return the searchName
     */
    public String getSearchName() {
        return searchName;
    }
}
