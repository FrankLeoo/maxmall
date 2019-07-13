
package com.maxmall.provider.merchant.service;

import com.maxmall.common.base.constant.AliyunMqTopicConstants;
import com.maxmall.common.core.message.MessageData;
import com.maxmall.provider.merchant.model.dto.email.SendEmailMessage;
import com.maxmall.provider.merchant.model.enums.UacEmailTemplateEnum;

import java.util.Map;
import java.util.Set;

/**
 * The interface Email service.
 *
 * @author maxmall.net@gmail.com
 */
public interface EmailService {

	/**
	 * 发送验证码
	 *
	 * @param sendEmailMessage the send email message
	 * @param loginName        the login name
	 */
	void sendEmailCode(SendEmailMessage sendEmailMessage, String loginName);

	/**
	 * 校验验证码 返回 token 用户最后修改密码使用
	 *
	 * @param sendEmailMessage the send email message
	 * @param loginName        the login name
	 */
	void checkEmailCode(SendEmailMessage sendEmailMessage, String loginName);

	/**
	 * 发送邮件
	 *
	 * @param emailSet
	 * @param emailTemplateEnum
	 * @param tagEnum
	 * @param param
	 * @return
	 */
	MessageData sendEmailMq(Set<String> emailSet, UacEmailTemplateEnum emailTemplateEnum, AliyunMqTopicConstants.MqTagEnum tagEnum, Map<String, Object> param);
}
