package com.maxmall.provider.product.model.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The class Mdc api constant.
 *
 * @author maxmall.net@gmail.com
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductApiConstant {

	/**
	 * The enum Product status enum.
	 *
	 * @author maxmall.net@gmail.com
	 */
	public enum PublishStatusEnum {
		/**
		 * On sale product status enum.
		 */
		ON_SALE(1, "在线"),
		/**
		 * Off sale product status enum.
		 */
		OFF_SALE(0, "下架");
		/**
		 * Delete product status enum.
		 */
		private String value;
		private int code;

		PublishStatusEnum(int code, String value) {
			this.code = code;
			this.value = value;
		}

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
	}
}
