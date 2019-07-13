package com.maxmall.provider.merchant.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.maxmall.common.base.constant.AliyunMqTopicConstants;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.message.MessageData;
import com.maxmall.common.core.redis.RedisService;
import com.maxmall.common.util.HttpAesUtil;
import com.maxmall.common.util.RandomUtil;
import com.maxmall.common.util.RedisKeyUtil;
import com.maxmall.provider.merchant.exception.UacBizException;
import com.maxmall.provider.merchant.model.dto.PcSendEmailRequest;
import com.maxmall.provider.merchant.model.dto.email.SendEmailMessage;
import com.maxmall.provider.merchant.model.enums.UacEmailTemplateEnum;
import com.maxmall.provider.merchant.service.EmailService;
import com.maxmall.provider.merchant.service.UacFreeMarkerService;
import com.xiaoleilu.hutool.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * The class Email service.
 *
 * @author maxmall.net@gmail.com
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

	@Value("${maxmall.auth.rest-pwd-url}")
	private String resetPwdUrl;
	@Resource
	private RedisService redisService;
	@Autowired
	private UacFreeMarkerService uacFreeMarkerService;

	private static final String KEY_STR = "om8q6fq#A0Yl@qJy";
	private static final String IV_STR = "0#86gzOcsv1bXyIx";


	@Override
	public void sendEmailCode(SendEmailMessage sendEmailMessage, String loginName) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(loginName), "用户名不能为空");
		String email = sendEmailMessage.getEmail();

		Preconditions.checkArgument(StringUtils.isNotEmpty(email), ErrorCodeEnum.UAC10011018.msg());
		Preconditions.checkArgument(StringUtils.isNotEmpty(loginName), ErrorCodeEnum.UAC10011007.msg());

		// 解密
		email = decryptEmail(loginName, email);

		String emailCode = RandomUtil.createNumberCode(6);
		String key = RedisKeyUtil.getSendEmailCodeKey(loginName, email);
		// 在redis中绑定验证码
		redisService.setKey(key, emailCode, 7 * 24, TimeUnit.HOURS);

		// 先写死 类型多了再抽方法
		Map<String, Object> param = Maps.newHashMap();
		param.put("loginName", loginName);
		param.put("email", email);
		param.put("emailCode", emailCode);
		param.put("dateTime", DateUtil.formatDateTime(new Date()));

		Set<String> to = Sets.newHashSet();
		to.add(email);

		MessageData messageData = this.sendEmailMq(to, UacEmailTemplateEnum.RESET_USER_EMAIL, AliyunMqTopicConstants.MqTagEnum.RESET_LOGIN_PWD, param);
	}

	@Override
	public void checkEmailCode(SendEmailMessage sendEmailMessage, String loginName) {

		String email = sendEmailMessage.getEmail();
		String emailCode = sendEmailMessage.getEmailCode();

		Preconditions.checkArgument(StringUtils.isNotEmpty(email), ErrorCodeEnum.UAC10011018.msg());
		Preconditions.checkArgument(StringUtils.isNotEmpty(emailCode), "验证码不能为空");

		// 解密用户名密码
		email = decryptEmail(loginName, email);
		String key = RedisKeyUtil.getSendEmailCodeKey(loginName, email);
		String emailCodeRedis = redisService.getKey(key);
		Preconditions.checkArgument(StringUtils.isNotEmpty(emailCodeRedis), "验证码已过期");
		Preconditions.checkArgument(StringUtils.equals(emailCode, emailCodeRedis), "验证码错误");
	}

	private String decryptEmail(final String loginName, String email) {
		try {
			email = HttpAesUtil.decrypt(email, KEY_STR, false, IV_STR);
			log.info("发送短信 解密loginName={}", loginName);
			log.info("发送短信 解密email={}", email);
		} catch (Exception ex) {
			log.info("发送短信 解密手机号码失败 密文loginName={}, email={}", loginName, email);
			throw new UacBizException(ErrorCodeEnum.UAC10011031);
		}
		return email;
	}

	/**
	 * Send email mq.
	 *
	 * @param emailSet          the email set
	 * @param emailTemplateEnum the email template enum
	 * @param param             the param
	 */
	@Override
	public MessageData sendEmailMq(Set<String> emailSet, UacEmailTemplateEnum emailTemplateEnum, AliyunMqTopicConstants.MqTagEnum tagEnum, Map<String, Object> param) {
		log.info("pcSendEmailRequest - 发送邮件MQ. emailSet={}, param={}", emailSet, param);
		String msgBody;
		try {

			Preconditions.checkArgument(emailTemplateEnum != null, "邮箱模板信息不存在");
			PcSendEmailRequest request = new PcSendEmailRequest();

			String templateLocation = emailTemplateEnum.getLocation();
			String text = uacFreeMarkerService.getTemplate(param, templateLocation);

			request.setText(text);
			request.setTo(emailSet);
			request.setSubject(emailTemplateEnum.getSubject());

			msgBody = JSON.toJSONString(request);
		} catch (Exception e) {
			log.error("发送邮件验证码 smsMessage转换为json字符串失败", e);
			throw new UacBizException(ErrorCodeEnum.UAC10011021);
		}
		String topic = tagEnum.getTopic();
		String tag = tagEnum.getTag();
		String key = RedisKeyUtil.createMqKey(topic, tag, emailSet.toString(), msgBody);
		return new MessageData(msgBody, topic, tag, key);
	}
}
