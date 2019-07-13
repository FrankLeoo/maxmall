
package com.maxmall.provider.merchant.model.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * The class Role bind user req dto.
 *
 * @author maxmall.net@gmail.com
 */
@Data
@ApiModel(value = "角色绑定用户")
public class RoleBindUserReqDto implements Serializable {
	private static final long serialVersionUID = 89217138744995863L;

	@NotNull
	@ApiModelProperty(value = "角色ID")
	private Long roleId;

	@NotNull
	@ApiModelProperty(value = "需要绑定的用户ID集合")
	private List<Long> userIdList;
}
