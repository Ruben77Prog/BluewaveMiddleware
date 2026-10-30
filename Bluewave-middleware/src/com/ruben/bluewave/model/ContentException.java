package com.ruben.bluewave.model;

import com.ruben.bluewave.service.ServiceException;

public class ContentException extends ServiceException {
	
	public ContentException(String message) {
		super(message);
	}

	
	public ContentException(String message, Throwable cause) {
		super(message, cause);
	}
}
