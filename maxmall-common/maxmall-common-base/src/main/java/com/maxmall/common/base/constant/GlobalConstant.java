package com.maxmall.common.base.constant;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * The class Global constant.
 *
 * @author maxmall.net@gmail.com
 */
public interface GlobalConstant {

	/**
	 * The constant FILE_MAX_SIZE.
	 */
	long FILE_MAX_SIZE = 5 * 1024 * 1024;
	String UNKNOWN = "unknown";

	String X_FORWARDED_FOR = "X-Forwarded-For";
	String X_REAL_IP = "X-Real-IP";
	String PROXY_CLIENT_IP = "Proxy-Client-IP";
	String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
	String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
	String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";


	String LOCALHOST_IP = "127.0.0.1";
	String LOCALHOST_IP_16 = "0:0:0:0:0:0:0:1";
	int MAX_IP_LENGTH = 15;

	int TWO_INT = 2;
	int M_SIZE = 1024;
	String ROOT_PREFIX = "maxmall";

	int EXCEPTION_CAUSE_MAX_LENGTH = 2048;
	int EXCEPTION_MESSAGE_MAX_LENGTH = 2048;

	String ZK_REGISTRY_ID_ROOT_PATH = "/sequence/maxmall/registry/id";
	String ZK_REGISTRY_SEQ = "/maxmall/seq";

	String PORTAL_PATH = "/portal";
	String COMMON_PATH = "/common";

	interface Number {
		int THOUSAND_INT = 1000;
		int HUNDRED_INT = 100;
		int ONE_INT = 1;
		int TWO_INT = 2;
		int THREE_INT = 3;
		int FOUR_INT = 4;
		int FIVE_INT = 5;
		int SIX_INT = 6;
		int SEVEN_INT = 7;
		int EIGHT_INT = 8;
		int NINE_INT = 9;
		int TEN_INT = 10;
		int EIGHTEEN_INT = 18;
	}


	/**
	 * 系统常量
	 */
	final class Sys {

		private Sys() {
		}

		/**
		 * 全局用户名
		 */
		public static final String TOKEN_AUTH_DTO = "CURRENT_USER_DTO";

		/**
		 * 超级管理员的用户ID
		 */
		public static final Long SUPER_MANAGER_USER_ID = 1L;
		/**
		 * 超级管理员的用户编号
		 */
		public static final String SUPER_MANAGER_LOGIN_NAME = "admin";
		/**
		 * 超级管理员角色ID
		 */
		public static final Long SUPER_MANAGER_ROLE_ID = 1L;
		/**
		 * 超级管理员组织ID
		 */
		public static final Long SUPER_MANAGER_GROUP_ID = 1L;
		/**
		 * 运营工作台ID
		 */
		public static final Long OPER_APPLICATION_ID = 1L;

		/**
		 * The constant MENU_ROOT.
		 */
		public static final String MENU_ROOT = "root";

		/**
		 * The constant DEFAULT_FILE_PATH.
		 */
		public static final String DEFAULT_FILE_PATH = "maxmall/file/";

		/**
		 * redis key default expire = 1MINUTES
		 */
		public static final long REDIS_DEFAULT_EXPIRE = 1L;
	}

	/**
	 * The class Symbol.
	 *
	 * @author maxmall.net@gmail.com
	 */
	final class Symbol {
		private Symbol() {
		}

		/**
		 * The constant COMMA.
		 */
		public static final String COMMA = ",";
		public static final String SPOT = ".";
		/**
		 * The constant UNDER_LINE.
		 */
		public final static String UNDER_LINE = "_";
		/**
		 * The constant PER_CENT.
		 */
		public final static String PER_CENT = "%";
		/**
		 * The constant AT.
		 */
		public final static String AT = "@";
		/**
		 * The constant PIPE.
		 */
		public final static String PIPE = "||";
		public final static String SHORT_LINE = "-";
		public final static String SPACE = " ";
		public static final String SLASH = "/";
		public static final String MH = ":";

	}

	/**
	 * The class Oss.
	 *
	 * @author maxmall.net@gmail.com
	 */
	final class Oss {
		private Oss() {
		}

		/**
		 * The constant DEFAULT_FILE_PATH.
		 */
		public static final String DEFAULT_FILE_PATH = "/default/";
	}


	/**
	 * 图片压缩高度和宽度
	 */
	int IMAGE_WIDTH = 1920;
	/**
	 * The constant IMAGE_HEIGHT.
	 */
	int IMAGE_HEIGHT = 1280;

	/**
	 * The constant Y.
	 */
	Integer Y = 1;
	/**
	 * The constant N.
	 */
	Integer N = 0;

	/**
	 * The enum Payment type enum.
	 *
	 * @author maxmall.net@gmail.com
	 */
	enum PaymentTypeEnum {
		/**
		 * Online pay payment type enum.
		 */
		ONLINE_PAY(1, "在线支付");

		PaymentTypeEnum(int code, String value) {
			this.code = code;
			this.value = value;
		}

		private String value;
		private int code;

		/**
		 * Gets value.
		 *
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Gets code.
		 *
		 * @return the code
		 */
		public int getCode() {
			return code;
		}

		/**
		 * Code of payment type enum.
		 *
		 * @param code the code
		 *
		 * @return the payment type enum
		 */
		public static PaymentTypeEnum codeOf(int code) {
			for (PaymentTypeEnum paymentTypeEnum : values()) {
				if (paymentTypeEnum.getCode() == code) {
					return paymentTypeEnum;
				}
			}
			return null;
		}

	}

	/**
	 * 状态枚举
	 */
	enum CommonStatusEnum {

		/**
		 * 启用
		 */
		ENABLE(0, "启用"),
		/**
		 * 禁用
		 */
		DISABLE(1, "禁用"),
		/**
		 * 审核中
		 */
		AUDIT(2, "审核中"),
		;

		/**
		 * The Status.
		 */
		int status;
		/**
		 * The Value.
		 */
		String message;

		/**
		 * Gets name.
		 *
		 * @param status the status
		 *
		 * @return the name
		 */
		public static String getName(int status) {
			for (CommonStatusEnum ele : CommonStatusEnum.values()) {
				if (status == ele.getStatus()) {
					return ele.getMessage();
				}
			}
			return null;
		}

		CommonStatusEnum(int status, String message) {
			this.status = status;
			this.message = message;
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
		public String getMessage() {
			return message;
		}

		private static List<Integer> getStatusList() {
			List<Integer> list = Lists.newArrayList();
			for (CommonStatusEnum ele : CommonStatusEnum.values()) {
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
}
