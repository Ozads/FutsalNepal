package com.ozads.futsalnepal.exceptions;

import org.hibernate.service.spi.ServiceException;

public class AlreadyExistException extends ServiceException{
	public AlreadyExistException(String message) {
		super(message);
	}

}
