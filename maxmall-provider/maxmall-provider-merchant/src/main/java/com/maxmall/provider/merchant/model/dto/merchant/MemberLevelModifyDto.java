package com.maxmall.provider.merchant.model.dto.merchant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "会员等级配置添加")
public class MemberLevelModifyDto implements Serializable {
    private Long id;

    @ApiModelProperty(value = "等级名称",required = true)
    @NotEmpty(message = "名称不能为空")
    @Length(max = 64)
    private String name;

    /**
     * 等级赠送优惠券ID.
     */
    @ApiModelProperty(value = "等级赠送优惠券ID")
    private Long growthCouponId;

    /**
     * 等级描述.
     */
    @ApiModelProperty(value = "等级描述")
    @Length(max = 255)
    private String description;

    /**
     * 等级赠送优惠券名称.
     */
    @ApiModelProperty(value = "赠送优惠券名称")
    @Length(max = 64)
    private String growthCouponName;

    /**
     * 等级.
     */
    @ApiModelProperty(value = "等级")
    @NotNull
    private Integer level;

    /**
     * 需要积分.
     */
    @ApiModelProperty(value = "需要积分")
    @NotNull
    private Integer growthPoint;
}
