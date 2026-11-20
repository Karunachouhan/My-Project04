package in.co.rays.proj4.exception;

import org.apache.log4j.Logger;

/**
 * DuplicateRecordException is a custom unchecked exception used when a
 * duplicate record is found in the application.
 *
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class DuplicateRecordException extends RuntimeException {

	private static Logger log = Logger.getLogger(DuplicateRecordException.class);

	/**
	 * Constructs a DuplicateRecordException with the specified error message.
	 *
	 * @param msg error message describing the duplicate record
	 */
	public DuplicateRecordException(String msg) {

		super(msg);

		log.debug("DuplicateRecordException constructor started");

		log.warn("Duplicate record found: " + msg);

		log.debug("DuplicateRecordException constructor ended");
	}

}