
package com.maxmall.provider.merchant.model.enums;

import java.util.Arrays;
import java.util.List;


/**
 * The enum Uac user source enum.
 *
 * @author maxmall.net@gmail.com
 */
public enum UserSourceEnum {

	/**
	 * 注册
	 */
	REGISTER( 1, "官网注册");

	/**
	 * The Key.
	 */
	int key;
	/**
	 * The message.
	 */
	String message;

	UserSourceEnum(int key, String message) {
		this.key = key;
		this.message = message;
	}

	/**
	 * Gets key.
	 *
	 * @return the key
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets value.
	 *
	 * @return the value
	 */
	public int getKey() {
		return key;
	}

	/**
	 * 获取key获取value
	 *
	 * @param key key
	 *
	 * @return value value
	 */
	public static String getValue(String key) {
		for (UserSourceEnum ele : UserSourceEnum.values()) {
			if (key.equals(ele.getKey())) {
				return ele.getMessage();
			}
		}
		return null;
	}

	/**
	 * 根据key获取该对象
	 *
	 * @param key key
	 *
	 * @return this enum
	 */
	public static UserSourceEnum getEnum(String key) {
		for (UserSourceEnum ele : UserSourceEnum.values()) {
			if (key.equals(ele.getKey())) {
				return ele;
			}
		}
		return null;
	}

	/**
	 * 获取List集合
	 *
	 * @return List list
	 */
	public static List<UserSourceEnum> getList() {
		return Arrays.asList(UserSourceEnum.values());
	}
}
