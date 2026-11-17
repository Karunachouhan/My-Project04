package in.co.rays.proj4.exception;

/**
 * DuplicateRecordException is a custom unchecked exception used when a
 * duplicate record is found in the application.
 *
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class DuplicateRecordException extends RuntimeException {

	/**
	 * Constructs a DuplicateRecordException with the specified error message.
	 *
	 * @param msg error message describing the duplicate record
	 */
	public DuplicateRecordException(String msg) {

		super(msg);

	}

}