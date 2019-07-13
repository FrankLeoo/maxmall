package com.maxmall.common.security.code.impl;

import com.maxmall.common.security.code.ValidateCode;
import com.maxmall.common.security.code.ValidateCodeException;
import com.maxmall.common.security.code.ValidateCodeRepository;
import com.maxmall.common.security.code.ValidateCodeType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * 基于redis的验证码存取器，避免由于没有session导致无法存取验证码的问题
 *
 * @author maxmall.net@gmail.com
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

	private final RedisTemplate<String, Object> redisTemplate;

	/**
	 * Instantiates a new Redis validate code repository.
	 *
	 * @param redisTemplate the redis template
	 */
	@Autowired
	public RedisValidateCodeRepository(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * Save.
	 *
	 * @param request the request
	 * @param code    the code
	 * @param type    the type
	 */
	@Override
	public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
		String key = buildKey(request, type);
		redisTemplate.opsForValue().set(key, code, 3, TimeUnit.MINUTES);
	}

	/**
	 * Get validate code.
	 *
	 * @param request the request
	 * @param type    the type
	 *
	 * @return the validate code
	 */
	@Override
	public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
		Object value = redisTemplate.opsForValue().get(buildKey(request, type));
		if (value == null) {
			return null;
		}
		return (ValidateCode) value;
	}

	/**
	 * Remove.
	 *
	 * @param request the request
	 * @param type    the type
	 */
	@Override
	public void remove(ServletWebRequest request, ValidateCodeType type) {
		redisTemplate.delete(buildKey(request, type));
	}

	private String buildKey(ServletWebRequest request, ValidateCodeType type) {
		String sessionId = request.getSessionId();

		return "code:" + type.toString().toLowerCase() + ":" + sessionId;
	}

}
