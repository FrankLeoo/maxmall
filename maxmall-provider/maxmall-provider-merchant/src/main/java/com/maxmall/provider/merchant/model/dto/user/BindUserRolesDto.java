
package com.maxmall.provider.merchant.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * The class Bind user roles dto.
 *
 * @author maxmall.net@gmail.com
 */
@ApiModel
@Data
public class BindUserRolesDto implements Serializable {

	private static final long serialVersionUID = -9149237379943908522L;

	@NotNull
	@ApiModelProperty(value = "角色ID")
	private Long userId;

	@ApiModelProperty(value = "需要绑定的角色ID集合")
	private List<Long> roleIdList;
}
