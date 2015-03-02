package org.voidsink.kussslib;

import org.voidsink.kussslib.impl.KusssHandlerImpl;

public class KusssHandlers {

	private static KusssHandler instance = null;
	private static Object mutex = new Object();
	
    public static KusssHandler getInstance() {
    	if (instance == null) {
			synchronized (mutex) {
				if (instance==null) instance= new KusssHandlerImpl();
			}
		}
		return instance;    
	}
	
	private KusssHandlers() {
		
	}	
}
