package com.maxmall.common.core.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * The class Token cache.
 *
 * @author maxmall.net@gmail.com
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenCache {

	public static final String NULL = "null";

	/**
	 * LRU算法
	 */
	private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS)
			.build(new CacheLoader<String, String>() {
				//默认的数据加载实现,当调用get取值的时候,如果key没有对应的值,就调用这个方法进行加载.
				@Override
				public String load(String s) {
					return "null";
				}
			});

	/**
	 * Set key.
	 *
	 * @param key   the key
	 * @param value the value
	 */
	public static void setKey(String key, String value) {
		localCache.put(key, value);
	}

	/**
	 * Get key string.
	 *
	 * @param key the key
	 *
	 * @return the string
	 */
	public static String getKey(String key) {
		String value;
		try {
			value = localCache.get(key);
			if (NULL.equals(value)) {
				return null;
			}
			return value;
		} catch (Exception e) {
			log.error("localCache get error", e);
		}
		return null;
	}
}
