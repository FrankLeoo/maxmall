package com.maxmall.provider.merchant.model.dto.role;

import com.maxmall.provider.merchant.model.vo.AccountVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * The class Role bind user dto.
 *
 * @author maxmall.net@gmail.com
 */
@Data
@ApiModel(value = "角色绑定用户")
public class RoleBindUserDto implements Serializable {

	private static final long serialVersionUID = -2521583668470612548L;
	/**
	 * 未绑定的用户集合
	 */
	@ApiModelProperty(value = "所有用户集合")
	private List<AccountVo> allUserSet;

	/**
	 * 已经绑定的用户集合
	 */
	@ApiModelProperty(value = "已经绑定的用户集合")
	private List<AccountVo> alreadyBindUserSet;
}
