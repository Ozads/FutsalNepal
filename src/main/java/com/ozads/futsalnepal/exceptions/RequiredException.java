package com.ozads.futsalnepal.exceptions;

import org.hibernate.service.spi.ServiceException;


@SuppressWarnings("serial")
public class RequiredException extends ServiceException  {

	/**
	 * @param message
	 */
	public RequiredException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
