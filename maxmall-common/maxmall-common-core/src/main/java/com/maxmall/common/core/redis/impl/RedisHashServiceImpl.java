
package com.maxmall.common.core.redis.impl;

import com.google.common.collect.Lists;
import com.maxmall.common.util.PublicUtil;
import com.maxmall.common.core.redis.RedisHashService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The class Redis hash service.
 *
 * @author maxmall.net@gmail.com
 */
@Slf4j
@Service
public class RedisHashServiceImpl implements RedisHashService {
	@Resource
	private StringRedisTemplate rt;

	@Override
	public <T> List<T> getValueByFields(String key, Set<String> fields) {
		HashOperations<String, String, T> hash = rt.opsForHash();
		if (!rt.hasKey(key)) {
			return Collections.emptyList();
		}
		List<T> values = hash.multiGet(key, fields);
		if (PublicUtil.isEmpty(values)) {
			return Collections.emptyList();
		}
		log.info("getValueByFields - 根据key获取所有给定字段的值. [OK] key={}, fields={}, values={}", key, fields, values);
		return values;

	}

	@Override
	public <T> List<T> getValueByField(String key, String field) {
		HashOperations<String, String, T> hash = rt.opsForHash();
		if (!rt.hasKey(key)) {
			return Collections.emptyList();
		}
		T value = hash.get(key, field);
		if (PublicUtil.isEmpty(value)) {
			return Collections.emptyList();
		}
		List<T> values = Lists.newArrayList();
		values.add(value);
		log.info("getValueByField - 根据key获取给定字段的值. [OK] key={}, field={}, values={}", key, field, values);
		return values;
	}

	@Override
	public void setValueByFields(String key, Map<String, Object> map) {
		HashOperations<String, String, Object> hash = rt.opsForHash();
		hash.putAll(key, map);
		log.info("setValueByFields - 同时将多个 field-value (域-值)对设置到哈希表 key 中. [ok] key={}, map={}", key, map);
	}

	@Override
	public Long removeFields(String key, String... hashKeys) {
		HashOperations<String, String, Object> hash = rt.opsForHash();
		Long result = hash.delete(key, (Object) hashKeys);
		log.info("removeFields- 删除一个或多个哈希表字段. [OK] key={}, hashKeys={}, result={}", key, hashKeys, result);
		return result;
	}
}
