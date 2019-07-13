package com.maxmall.provider.marketing.web;


import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.base.exception.BusinessException;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 全局的的异常拦截器
 *
 * @author maxmall.net@gmail.com
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@Resource
	private TaskExecutor taskExecutor;

	/**
	 * 参数非法异常.
	 *
	 * @param e the e
	 *
	 * @return the wrapper
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Wrapper illegalArgumentException(IllegalArgumentException e) {
		log.error("参数非法异常={}", e.getMessage(), e);
		return WrapMapper.wrap(ErrorCodeEnum.GL99990100.code(), e.getMessage());
	}

	/**
	 * 业务异常.
	 *
	 * @param e the e
	 *
	 * @return the wrapper
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Wrapper businessException(BusinessException e) {
		log.error("业务异常={}", e.getMessage(), e);
		return WrapMapper.wrap(e.getCode() == 0 ? Wrapper.ERROR_CODE : e.getCode(), e.getMessage());
	}

	/**
	 * 无权限访问.
	 *
	 * @param e the e
	 *
	 * @return the wrapper
	 */
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public Wrapper unAuthorizedException(AccessDeniedException e) {
		log.error("业务异常={}", e.getMessage(), e);
		return WrapMapper.wrap(ErrorCodeEnum.GL99990401.code(), ErrorCodeEnum.GL99990401.msg());
	}


	/**
	 * 全局异常.
	 *
	 * @param e the e
	 *
	 * @return the wrapper
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Wrapper exception(Exception e) {
		log.info("保存全局异常信息 ex={}", e.getMessage(), e);

		if (e instanceof MethodArgumentNotValidException){
			MethodArgumentNotValidException  ex = (MethodArgumentNotValidException)e;
			StringBuilder errorMsg = new StringBuilder();
			for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
				String msg = String.format("%s value '%s' %s", fieldError.getField(),
						fieldError.getRejectedValue(), fieldError.getDefaultMessage());
				errorMsg.append(msg + ";");
			}
			return WrapMapper.error(Wrapper.ILLEGAL_ARGUMENT_CODE_, errorMsg.toString());
		}else if( e instanceof BusinessException){
			BusinessException ex = (BusinessException)e;
			return WrapMapper.error(ex.getCode(),ex.getMessage());
		}else if(e instanceof ConstraintViolationException){
			ConstraintViolationException ex = (ConstraintViolationException)e;

			StringBuilder errorMsg = new StringBuilder();
			for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
				String msg = String.format("%s value '%s' %s", constraintViolation.getPropertyPath(),
						constraintViolation.getInvalidValue(), constraintViolation.getMessage());
				errorMsg.append(msg + ";");
			}
			return WrapMapper.error(Wrapper.ILLEGAL_ARGUMENT_CODE_, errorMsg.toString());
		}else{
			return WrapMapper.error();
		}

	}
}
