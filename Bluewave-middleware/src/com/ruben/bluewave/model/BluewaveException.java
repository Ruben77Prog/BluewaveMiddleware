package com.ruben.bluewave.model;

public class BluewaveException extends Exception {

	public BluewaveException(String message) {
		super(message); 
	}
	
	public BluewaveException(Throwable cause) { 
		super(cause); 
	}
	
	public BluewaveException(String message, Throwable cause) { 
		super(message, cause); 
	}	
}