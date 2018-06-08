package com.ozads.futsalnepal.exceptions;

import org.hibernate.service.spi.ServiceException;


public class ValidationException extends ServiceException {

	public ValidationException(String message) {
		super(message);
	}

}
