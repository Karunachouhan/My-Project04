package in.co.rays.proj4.exception;

import org.apache.log4j.Logger;

/**
 * ApplicationException is a custom unchecked exception used to handle
 * application-specific errors.
 *
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class ApplicationException extends RuntimeException {

	private static Logger log = Logger.getLogger(ApplicationException.class);

	/**
	 * Constructs an ApplicationException with the specified error message.
	 *
	 * @param msg error message describing the exception
	 */
	public ApplicationException(String msg) {

		super(msg);

		log.debug("ApplicationException constructor started");

		log.error("ApplicationException occurred: " + msg);

		log.debug("ApplicationException constructor ended");
	}

}