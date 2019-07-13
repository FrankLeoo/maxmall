
package com.maxmall.provider.merchant.service;

import com.maxmall.common.base.constant.AliyunSmsConstants;
import com.maxmall.common.core.message.MessageData;
import com.maxmall.provider.merchant.model.dto.sms.SmsMessage;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletResponse;


/**
 * The interface Sms service.
 *
 * @author maxmall.net@gmail.com
 */
public interface SmsService {
	/**
	 * Send sms code.
	 *
	 * @param smsMessage the sms message
	 * @param ipAddr     the ip addr
	 */
	void sendSmsCode(SmsMessage smsMessage, String ipAddr);

	/**
	 * Submit reset pwd phone.
	 *
	 * @param mobile   the mobile
	 *
	 * @return the string
	 */
	String submitResetPwdPhone(String mobile, ServletWebRequest request);

	/**
	 * 发送短信信息
	 * @param smsMessage
	 * @param templetEnum
	 * @return
	 */
	MessageData sendSmsCodeMq(SmsMessage smsMessage, AliyunSmsConstants.SmsTempletEnum templetEnum);
}
