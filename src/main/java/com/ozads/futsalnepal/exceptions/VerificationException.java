package com.ozads.futsalnepal.exceptions;

import org.hibernate.service.spi.ServiceException;


@SuppressWarnings("serial")
public class VerificationException extends ServiceException {

	/**
	 * @param message
	 */
	public VerificationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
