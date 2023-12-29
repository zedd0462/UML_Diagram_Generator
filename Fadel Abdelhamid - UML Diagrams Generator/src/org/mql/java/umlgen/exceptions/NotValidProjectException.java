package org.mql.java.umlgen.exceptions;

/**
 * Exception thrown when the given path is not a valid Java Project.
 * Including but not limited to the following reasons:
 * <ul>
 * <li>Path is malformed</li>
 * <li>Path does not exist</li>
 * <li>Path is not a directory</li>
 * <li>Directory does not have .class files including its subfolders</li>
 * </ul>
 */
public class NotValidProjectException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotValidProjectException() {
		super("The project path specified is not valid.");
	}


	public NotValidProjectException(Throwable cause) {
		super("The project path specified is not valid.",cause);
	}


}
