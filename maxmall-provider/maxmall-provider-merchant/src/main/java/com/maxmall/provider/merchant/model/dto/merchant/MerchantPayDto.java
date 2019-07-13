package com.maxmall.provider.merchant.model.dto.merchant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "角色绑定用户")
public class MerchantPayDto implements Serializable {

    @NotNull
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    /**
     * 支付账号.
     */
    @ApiModelProperty(value = "支付账号")
    private String payAccount;

    /**
     * 支付类型 个人|商户.
     */
    @ApiModelProperty(value = "支付类型 个人|商户")
    private Integer payType;
}
