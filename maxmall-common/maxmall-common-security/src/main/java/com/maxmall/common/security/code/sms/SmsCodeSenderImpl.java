
package com.maxmall.common.security.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认的短信验证码发送器
 *
 * @author maxmall.net@gmail.com
 */
@Slf4j
public class SmsCodeSenderImpl implements SmsCodeSender {

	@Override
	public void send(String mobile, String code, String ip) {
		log.info("ip地址:{}向手机: {}发送短信验证码:{}", ip, mobile, code);
	}

}
