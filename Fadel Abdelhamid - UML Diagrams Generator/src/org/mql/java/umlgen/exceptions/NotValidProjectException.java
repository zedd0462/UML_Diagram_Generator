package org.mql.java.umlgen.exceptions;

public class NotValidProjectException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotValidProjectException() {
		super("The project path specified is not valid.");
	}


	public NotValidProjectException(Throwable cause) {
		super("The project path specified is not valid.",cause);
	}


}
