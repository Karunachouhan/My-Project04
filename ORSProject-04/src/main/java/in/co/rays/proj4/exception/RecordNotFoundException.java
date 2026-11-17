package in.co.rays.proj4.exception;

/**
 * RecordNotFoundException is a custom unchecked exception used when the
 * requested record is not found in the application.
 *
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class RecordNotFoundException extends RuntimeException {

	/**
	 * Constructs a RecordNotFoundException with the specified error message.
	 *
	 * @param msg error message describing the record not found
	 */
	public RecordNotFoundException(String msg) {

		super(msg);

	}

}