package com.jpmorgan.report.exception;

public class MessageException extends RuntimeException{
	private static final long serialVersionUID = -5606228291378058448L;

	/**
	 * 
	 */
	public MessageException(String message){
		super(message);
	}
}
