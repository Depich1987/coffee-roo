package com.j1987.coffeeroo.framework;

public class CoffeeRooGenericException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7790616169617166724L;

	public CoffeeRooGenericException() {
		
	}

	public CoffeeRooGenericException(String message) {
		super(message);
	}

	public CoffeeRooGenericException(Throwable cause) {
		super(cause);
	}

	public CoffeeRooGenericException(String message, Throwable cause) {
		super(message, cause);
	}

}
