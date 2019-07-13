
package com.maxmall.provider.product.exception;

import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * The class Uac biz exception.
 *
 * @author maxmall.net@gmail.com
 */
@Slf4j
public class ProductBizException extends BusinessException {

	private static final long serialVersionUID = -6552248511084911254L;

	/**
	 * Instantiates a new Uac rpc exception.
	 */
	public ProductBizException() {
	}

	/**
	 * Instantiates a new Uac rpc exception.
	 *
	 * @param code      the code
	 * @param msgFormat the msg format
	 * @param args      the args
	 */
	public ProductBizException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
		log.info("<== ProductRpcException, code:" + this.code + ", message:" + super.getMessage());

	}

	/**
	 * Instantiates a new Uac rpc exception.
	 *
	 * @param code the code
	 * @param msg  the msg
	 */
	public ProductBizException(int code, String msg) {
		super(code, msg);
		log.info("<== ProductRpcException, code:" + this.code + ", message:" + super.getMessage());
	}

	/**
	 * Instantiates a new Uac rpc exception.
	 *
	 * @param codeEnum the code enum
	 */
	public ProductBizException(ErrorCodeEnum codeEnum) {
		super(codeEnum.code(), codeEnum.msg());
		log.info("<== ProductRpcException, code:" + this.code + ", message:" + super.getMessage());
	}

	/**
	 * Instantiates a new Uac rpc exception.
	 *
	 * @param codeEnum the code enum
	 * @param args     the args
	 */
	public ProductBizException(ErrorCodeEnum codeEnum, Object... args) {
		super(codeEnum, args);
		log.info("<== OpcRpcException, code:" + this.code + ", message:" + super.getMessage());
	}
}
