package com.globalsqa.common.exception;

/**
 * Extends the {@link RuntimeException} in order to specify the domain where this exception occurs
 *
 * @author Ievgen_Ostapenko
 */
public class NoSuchRowFoundException extends RuntimeException
{
	public NoSuchRowFoundException(String message)
	{
		super(message);
	}

	public NoSuchRowFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
