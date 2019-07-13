package com.maxmall.common.util.exception;


/**
 * The class Http aes exception.
 *
 * @author maxmall.net @gmail.com
 */
public class HttpAesException extends IllegalArgumentException {
	private static final long serialVersionUID = -2489600753056921095L;

	/**
	 * Instantiates a new Http aes exception.
	 *
	 * @param message the message
	 */
	public HttpAesException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Http aes exception.
	 */
	public HttpAesException() {

	}
}
