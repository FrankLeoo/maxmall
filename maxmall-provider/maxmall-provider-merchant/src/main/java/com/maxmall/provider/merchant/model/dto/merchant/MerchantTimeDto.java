package com.maxmall.provider.merchant.model.dto.merchant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "角色绑定用户")
public class MerchantTimeDto implements Serializable {

    /**
     * 自动完成交易时间，不能申请售后（天）默认30天.
     */
    @ApiModelProperty(value = "自动完成交易时间，不能申请售后（天）默认30天")
    private Integer finishOvertime;

    /**
     * 订单完成后自动好评时间（天）默认7天.
     */
    @ApiModelProperty(value = "订单完成后自动好评时间（天）默认7天")
    private Integer commentOvertime;

    /**
     * 发货后自动确认收货时间（天） 默认3天.
     */
    @ApiModelProperty(value = "发货后自动确认收货时间（天） 默认3天")
    private Integer confirmOvertime;

    /**
     * 秒杀订单超时关闭时间(分) 默认10分钟.
     */
    @ApiModelProperty(value = "秒杀订单超时关闭时间(分) 默认10分钟")
    private Integer flashOrderOvertime;

    /**
     * 正常订单超时时间(分) 默认15分钟.
     */
    @ApiModelProperty(value = "正常订单超时时间(分) 默认15分钟")
    private Integer normalOrderOvertime;
}
