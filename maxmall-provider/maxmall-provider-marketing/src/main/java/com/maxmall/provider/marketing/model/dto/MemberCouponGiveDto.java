package com.maxmall.provider.marketing.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "优惠券新增参数")
public class MemberCouponGiveDto implements Serializable {

    @NotNull
    @ApiModelProperty(value = "优惠券id")
    private Long couponId;

    @NotNull
    @ApiModelProperty(value = "会员id")
    private Long memberId;

}
