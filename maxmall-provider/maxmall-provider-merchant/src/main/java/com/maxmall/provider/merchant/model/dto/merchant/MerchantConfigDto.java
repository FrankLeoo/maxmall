package com.maxmall.provider.merchant.model.dto.merchant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "角色绑定用户")
public class MerchantConfigDto implements Serializable {

    /**
     * 是否开启积分属性.
     */
    @ApiModelProperty(value = "是否开启积分属性")
    private Boolean isPoint;

    /**
     * 是否开启会员成长.
     */
    @ApiModelProperty(value = "是否开启会员成长")
    private Boolean isGrowth;

    /**
     * 是否开启分销.
     */
    @ApiModelProperty(value = "是否开启分销")
    private Boolean isDistributor;

    /**
     * 是否开启返点.
     */
    @ApiModelProperty(value = "是否开启返点")
    private Boolean isCommission;

    /**
     * 积分兑换1元.
     */
    @ApiModelProperty(value = "积分兑换1元")
    private Integer pointCharge;

    /**
     * 消费1元=多少成长值.
     */
    @ApiModelProperty(value = "消费1元=多少成长值")
    private Integer growthCharge;

    /**
     * 积分使用限制（单最高抵用百分比）.
     */
    @ApiModelProperty(value = "积分使用限制（单最高抵用百分比）")
    private Integer usePointLimit;

}
