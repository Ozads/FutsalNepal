package com.ozads.futsalnepal.exceptions;

import org.hibernate.service.spi.ServiceException;


@SuppressWarnings("serial")
public class ExpireException extends ServiceException {

	
	public ExpireException(String message) {
		super(message);
	}

}
