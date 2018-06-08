package com.ozads.futsalnepal.exceptions;

import org.hibernate.service.spi.ServiceException;

public class RequiredMoreException extends ServiceException {
	
	public RequiredMoreException(String string) {
		super(string);
	}
	
}
