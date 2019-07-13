package com.maxmall.common.core.mybatis;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maxmall.common.base.dto.UserTokenDto;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The class Base entity.
 *
 * @author maxmall.net@gmail.com
 */
@Data
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 2393269568666085258L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 创建人
	 */
	@Column(name = "creator")
	private String creator;

	/**
	 * 创建人ID
	 */
	@Column(name = "creator_id")
	private Long creatorId;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 最近操作人
	 */
	@Column(name = "last_operator")
	private String lastOperator;

	/**
	 * 最后操作人ID
	 */
	@Column(name = "last_operator_id")
	private Long lastOperatorId;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;

	@Transient
	private Integer pageNum;

	@Transient
	private Integer pageSize;

	@Transient
	private String orderBy;

	/**
	 * Is new boolean.
	 *
	 * @return the boolean
	 */
	@Transient
	@JsonIgnore
	public boolean isNew() {
		return this.id == null;
	}

	/**
	 * Sets update info.
	 *
	 * @param user the user
	 */
	@Transient
	@JsonIgnore
	public void setUpdateInfo(UserTokenDto user) {

		if (isNew()) {
			this.creatorId = (this.lastOperatorId = user.getUserId());
			this.creator = user.getUserName();
			this.createTime = (this.updateTime = new Date());
		}
		this.lastOperatorId = user.getUserId();
		this.lastOperator = user.getUserName() == null ? user.getLoginName() : user.getUserName();
		this.updateTime = new Date();
	}
}
