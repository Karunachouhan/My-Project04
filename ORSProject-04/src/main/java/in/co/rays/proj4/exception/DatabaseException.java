package in.co.rays.proj4.exception;

/**
 * DatabaseException is a custom unchecked exception used to handle
 * database-related errors.
 *
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class DatabaseException extends RuntimeException {

	/**
	 * Constructs a DatabaseException with the specified error message.
	 *
	 * @param msg error message describing the database exception
	 */
	public DatabaseException(String msg) {

		super(msg);

	}

}