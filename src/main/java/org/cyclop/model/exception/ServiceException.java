package org.cyclop.model.exception;

import net.jcip.annotations.Immutable;

/** @author Maciej Miklas */
@Immutable
public class ServiceException extends RuntimeException {
	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Exception cause) {
		super(message, cause);
	}
}
