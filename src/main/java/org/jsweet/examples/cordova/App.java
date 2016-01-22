package org.jsweet.examples.cordova;
import static jsweet.util.StringTypes.deviceready;
import static jsweet.dom.Globals.console;
import static jsweet.dom.Globals.document;

import jsweet.dom.Element;
import jsweet.dom.Event;

public class App {
	
	public static App instance = new App();
	
	App() {}
	
	 // Application Constructor
    public void initialize() {
        this.bindEvents();
    }
    
    // Bind Event Listeners
    //
    // Bind any events that are required on startup. Common events are:
    // "load", "deviceready", "offline", and "online".
    public void bindEvents() {
        document.addEventListener(deviceready, this::onDeviceReady, false);
    }
    // deviceready Event Handler
    //
    // The scope of "this" is the event. In order to call the "receivedEvent"
    // function, we must explicitly call "app.receivedEvent(...);"
    public Object onDeviceReady(Object e) {
        receivedEvent("deviceready");
        return null;
    }
    // Update DOM on a Received Event
    public void receivedEvent(String id) {
        Element parentElement = document.getElementById(id);
        Element listeningElement = parentElement.querySelector(".listening");
        Element receivedElement = parentElement.querySelector(".received");

        listeningElement.setAttribute("style", "display:none;");
        receivedElement.setAttribute("style", "display:block;");

        console.log("Received Event: " + id);
    }
    
    public static void main(String[] args) {
    	App.instance.initialize();
	}
}

