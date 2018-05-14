package com.ifworks.s4.exception;

public class S4Exception extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6685914012122989532L;

	public S4Exception(String message) {
		super(message);
	}

	public S4Exception(Exception e) {
		super(e.getMessage(), e);
	}

	public S4Exception(String message, Exception e) {
		super(message, e);
	}
}