package in.co.rays.proj4.exception;

/**
 * ApplicationException is a custom unchecked exception used to handle
 * application-specific errors.
 *
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class ApplicationException extends RuntimeException {

	/**
	 * Constructs an ApplicationException with the specified error message.
	 *
	 * @param msg error message describing the exception
	 */
	public ApplicationException(String msg) {

		super(msg);

	}

}