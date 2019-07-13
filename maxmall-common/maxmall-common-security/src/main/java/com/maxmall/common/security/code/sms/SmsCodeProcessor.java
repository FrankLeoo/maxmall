package com.maxmall.common.security.code.sms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmall.common.security.SecurityResult;
import com.maxmall.common.security.code.ValidateCode;
import com.maxmall.common.security.code.ValidateCodeException;
import com.maxmall.common.security.code.ValidateCodeGenerator;
import com.maxmall.common.security.code.ValidateCodeRepository;
import com.maxmall.common.security.code.impl.AbstractValidateCodeProcessor;
import com.maxmall.common.security.properties.SecurityConstants;
import com.maxmall.common.security.properties.SecurityProperties;
import com.maxmall.common.security.properties.SmsCodeProperties;
import com.maxmall.common.util.RedisKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 短信验证码处理器
 *
 * @author maxmall.net@gmail.com
 */
@Component("smsValidateCodeProcessor")
@Slf4j
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	private static final String X_FORWARDED_FOR = "x-forwarded-for";
	private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
	private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

	private static final String LOCALHOST_IP = "127.0.0.1";
	private static final String LOCALHOST_IP_16 = "0:0:0:0:0:0:0:1";
	private static final String UNKNOWN = "unknown";
	private static final String COMMA = ",";
	private static final int MAX_IP_LENGTH = 15;

	@Resource
	private SmsCodeSender smsCodeSender;
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private SecurityProperties securityProperties;
	@Resource
	private ObjectMapper objectMapper;

	/**
	 * Instantiates a new Abstract validate code processor.
	 *
	 * @param validateCodeGenerators the validate code generators
	 * @param validateCodeRepository the validate code repository
	 */
	public SmsCodeProcessor(Map<String, ValidateCodeGenerator> validateCodeGenerators, ValidateCodeRepository validateCodeRepository) {
		super(validateCodeGenerators, validateCodeRepository);
	}

	/**
	 * Send.
	 *
	 * @param request      the request
	 * @param validateCode the validate code
	 *
	 * @throws Exception the exception
	 */
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		String ipAddr = this.getRemoteAddr(request.getRequest());
		SecurityResult result = new SecurityResult(SecurityResult.SUCCESS_CODE, "校验成功", true);
		// 统一处理短信流量
		try {
			this.checkSendSmsCount(mobile, ipAddr);
			smsCodeSender.send(mobile, validateCode.getCode(), ipAddr);
		} catch (ValidateCodeException e) {
			log.error("校验短信数量, e={}", e.getMessage(), e);
			result = SecurityResult.error(e.getMessage(), false);
		} catch (Exception e) {
			log.error("校验短信数量, e={}", e.getMessage(), e);
			result = SecurityResult.error("内部异常", false);
		}
		String json = objectMapper.writeValueAsString(result);
		HttpServletResponse response = request.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * 发送短信 并返回状态
	 * @param request
	 * @param mobile
	 * @param vlideCode
	 * @throws Exception
	 */
	public void send(ServletWebRequest request, String mobile,String vlideCode)throws Exception{
		String ipAddr = this.getRemoteAddr(request.getRequest());
		// 统一处理短信流量
		this.checkSendSmsCount(mobile, ipAddr);
		smsCodeSender.send(mobile, vlideCode, ipAddr);
	}

	private void checkSendSmsCount(String mobile, String ipAddr) {
		String mobileSmsCountKey = RedisKeyUtil.getSendSmsCountKey(mobile, "mobile");
		String ipSmsCountKey = RedisKeyUtil.getSendSmsCountKey(ipAddr, "ip");
		String totalSmsCountKey = RedisKeyUtil.getSendSmsCountKey("total", "total");
		String sendSmsRateKey = RedisKeyUtil.getSendSmsRateKey(ipAddr);
		SmsCodeProperties sms = securityProperties.getCode().getSms();

		Integer sendSmsRateCount = (Integer) redisTemplate.opsForValue().get(sendSmsRateKey);
		if (sendSmsRateCount != null) {
			log.error("操作频率过快 ipAddr={}, mobile={}", ipAddr, mobile);
			throw new ValidateCodeException("操作频率过快");
		} else {
			redisTemplate.opsForValue().set(sendSmsRateKey, 1, 1, TimeUnit.MINUTES);
		}

		Integer mobileSmsCount = (Integer) redisTemplate.opsForValue().get(mobileSmsCountKey);
		if (mobileSmsCount != null && mobileSmsCount > sms.getMobileMaxSendCount()) {
			log.error("Mobile当天短信发送数上限 ipAddr={}, mobile={}", ipAddr, mobile);
			throw new ValidateCodeException("Mobile当天短信发送数上限");
		} else {
			redisTemplate.opsForValue().set(mobileSmsCountKey, mobileSmsCount == null ? 1 : mobileSmsCount + 1, 1, TimeUnit.DAYS);
		}
		Integer ipSmsCount = (Integer) redisTemplate.opsForValue().get(ipSmsCountKey);
		if (ipSmsCount != null && ipSmsCount > sms.getIpMaxSendCount()) {
			log.error("IP当天短信发送数上限 ipAddr={}, mobile={}", ipAddr, mobile);
			throw new ValidateCodeException("IP当天短信发送数上限");
		} else {
			redisTemplate.opsForValue().set(ipSmsCountKey, ipSmsCount == null ? 1 : ipSmsCount + 1, 1, TimeUnit.DAYS);
		}
		Integer totalSmsCount = (Integer) redisTemplate.opsForValue().get(totalSmsCountKey);
		if (totalSmsCount != null && totalSmsCount > sms.getTotalMaxSendCount()) {
			log.error("当天短信发送数上限 ipAddr={}, mobile={}", ipAddr, mobile);
			throw new ValidateCodeException("当天短信发送数上限");
		} else {
			redisTemplate.opsForValue().set(totalSmsCountKey, totalSmsCount == null ? 1 : totalSmsCount + 1, 1, TimeUnit.DAYS);
		}
	}

	private String getRemoteAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader(X_FORWARDED_FOR);
		if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(PROXY_CLIENT_IP);
		}
		if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(WL_PROXY_CLIENT_IP);
		}
		if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (LOCALHOST_IP.equals(ipAddress) || LOCALHOST_IP_16.equals(ipAddress)) {
				//根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					log.error("获取IP地址, 出现异常={}", e.getMessage(), e);
				}
				assert inet != null;
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况, 第一个IP为客户端真实IP,多个IP按照','分割 //"***.***.***.***".length() = 15
		if (ipAddress != null && ipAddress.length() > MAX_IP_LENGTH && ipAddress.indexOf(COMMA) > 0) {
			ipAddress = ipAddress.substring(0, ipAddress.indexOf(COMMA));
		}
		return ipAddress;
	}
}
