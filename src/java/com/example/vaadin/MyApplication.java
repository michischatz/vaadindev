/*
 * MyApplication.java
 *
 * Created on 5. Oktober 2012, 19:54
 */
 
package com.example.vaadin;           

import com.vaadin.Application;
import com.vaadin.ui.*;
import com.vaadin.data.*;
/** 
 *
 * @author Michael
 * @version 
 */

public class MyApplication extends Application {

    @Override
    public void init() {
	Window mainWindow = new Window("MyApplication");
        Label label = new Label("Hello Vaadin user");
	mainWindow.addComponent(label);
	setMainWindow(mainWindow);
    }

}
