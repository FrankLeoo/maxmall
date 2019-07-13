package com.maxmall.common.security.code.email;

/**
 * The interface Sms code sender.
 *
 * @author maxmall.net@gmail.com
 */
public interface EmailCodeSender {

	/**
	 * Send.
	 *
	 * @param email the email
	 * @param code  the code
	 */
	void send(String email, String code);

}
