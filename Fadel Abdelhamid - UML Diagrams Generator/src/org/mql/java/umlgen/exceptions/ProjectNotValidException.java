package org.mql.java.umlgen.exceptions;

public class ProjectNotValidException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProjectNotValidException() {
		super("The project path specified is not valid.");
	}


	public ProjectNotValidException(Throwable cause) {
		super("The project path specified is not valid.",cause);
	}


}
