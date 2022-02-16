package com.ssuarez.blogsystem.exceptions;

import org.springframework.http.HttpStatus;

public class BlogException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private HttpStatus state;
	private String message;
	
	public BlogException(HttpStatus state, String message) {
		super();
		this.state = state;
		this.message = message;
	}
	
	public HttpStatus getState() {
		return state;
	}

	public void setState(HttpStatus state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
