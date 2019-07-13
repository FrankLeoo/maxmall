package com.maxmall.provider.marketing.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

/**
 * 订单查询参数
 * Created by macro on 2018/10/11.
 */
@Data
@ApiModel(value = "用户优惠券查询参数")
public class MemberCouponQueryDto {

    @ApiModelProperty(value = "优惠券名称")
    private String name;

    @ApiModelProperty(value = "优惠券类型")
    private Integer type;

    /**
     * 优惠券id.
     */
    @ApiModelProperty(value = "优惠券id")
    private Long couponId;

    /**
     * C端用户会员id.
     */
    @ApiModelProperty(value = "用户id")
    private Long memberId;

    @ApiModelProperty(value = "用户名称")
    private String memberName;

    @ApiModelProperty(value = "订单编号")
    private String usedSource;

    /**
     * 优惠码.
     */
    @ApiModelProperty(value = "优惠券编码")
    private String code;

    private Integer pageNum;

    private Integer pageSize;
}
