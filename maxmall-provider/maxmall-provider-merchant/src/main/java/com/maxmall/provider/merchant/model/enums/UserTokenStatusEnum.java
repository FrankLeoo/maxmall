
package com.maxmall.provider.merchant.model.enums;

import com.google.common.collect.Lists;

import java.util.List;


/**
 * The enum Uac user token status enum.
 *
 * @author maxmall.net@gmail.com
 */
public enum UserTokenStatusEnum {
	/**
	 * 启用
	 */
	ON_LINE(0, "在线"),
	/**
	 * 已刷新
	 */
	ON_REFRESH(10, "已刷新"),
	/**
	 * 离线
	 */
	OFF_LINE(20, "离线");

	/**
	 * The Status.
	 */
	int status;
	/**
	 * The Value.
	 */
	String value;

	/**
	 * Gets name.
	 *
	 * @param status the status
	 *
	 * @return the name
	 */
	public static String getName(int status) {
		for (UserTokenStatusEnum ele : UserTokenStatusEnum.values()) {
			if (status == ele.getStatus()) {
				return ele.getValue();
			}
		}
		return null;
	}

	UserTokenStatusEnum(int status, String value) {
		this.status = status;
		this.value = value;
	}

	/**
	 * Gets status.
	 *
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Gets value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	private static List<Integer> getStatusList() {
		List<Integer> list = Lists.newArrayList();
		for (UserTokenStatusEnum ele : UserTokenStatusEnum.values()) {
			list.add(ele.getStatus());
		}
		return list;
	}

	/**
	 * Contains boolean.
	 *
	 * @param status the status
	 *
	 * @return the boolean
	 */
	public static boolean contains(Integer status) {
		List<Integer> statusList = getStatusList();
		return statusList.contains(status);
	}
}