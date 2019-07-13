package com.maxmall.common.core.annotation;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * The class Operation log dto.
 *
 * @author maxmall.net@gmail.com
 */
@Data
public class OperationLogDto implements Serializable {
	private static final long serialVersionUID = -5606865665592482762L;
	private Long id;

	/**
	 * 组织流水号
	 */
	private Long groupId;

	/**
	 * 组织名称
	 */
	private String groupName;

	/**
	 * 日志类型
	 */
	private String logType;

	/**
	 * 日志类型名称
	 */
	private String logName;

	/**
	 * 权限ID
	 */
	private Long actionId;

	/**
	 * 权限编码
	 */
	private String actionCode;

	/**
	 * 权限名称
	 */
	private String actionName;

	/**
	 * 操作系统
	 */
	private String os;

	/**
	 * 浏览器类型
	 */
	private String browser;

	/**
	 * IP地址
	 */
	private String ip;

	/**
	 * 操作位置
	 */
	private String location;

	/**
	 * 物理地址
	 */
	private String mac;

	/**
	 * 详细描述
	 */
	private String description;

	/**
	 * 请求参数
	 */
	private String requestData;

	/**
	 * 请求地址
	 */
	private String requestUrl;

	/**
	 * 响应结果
	 */
	private String responseData;

	/**
	 * 类名
	 */
	private String className;

	/**
	 * 方法名
	 */
	private String methodName;

	/**
	 * 开始时间
	 */
	private Date startTime;

	/**
	 * 结束时间
	 */
	private Date endTime;

	/**
	 * 耗时,秒
	 */
	private Long excuteTime;

	/**
	 * 创建人
	 */
	private String creator;

	/**
	 * 创建人ID
	 */
	private Long creatorId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 最近操作人
	 */
	private String lastOperator;

	/**
	 * 最后操作人ID
	 */
	private Long lastOperatorId;

	/**
	 * 更新时间
	 */
	private Date updateTime;

}