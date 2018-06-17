package com.ozads.futsalnepal.exceptions;

import org.hibernate.service.spi.ServiceException;


@SuppressWarnings("serial")
public class NotValidExpection extends ServiceException {

	/**
	 * @param message
	 */
	public NotValidExpection(String message) {
		super(message);
	}

}
