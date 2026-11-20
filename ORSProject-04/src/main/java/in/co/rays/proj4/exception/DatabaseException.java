package in.co.rays.proj4.exception;

import org.apache.log4j.Logger;

/**
 * DatabaseException is a custom unchecked exception used to handle
 * database-related errors.
 *
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class DatabaseException extends RuntimeException {

	private static Logger log = Logger.getLogger(DatabaseException.class);

	/**
	 * Constructs a DatabaseException with the specified error message.
	 *
	 * @param msg error message describing the database exception
	 */
	public DatabaseException(String msg) {

		super(msg);

		log.debug("DatabaseException constructor started");

		log.error("DatabaseException occurred: " + msg);

		log.debug("DatabaseException constructor ended");
	}

}