package com.maxmall.provider.marketing.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单查询参数
 * Created by macro on 2018/10/11.
 */
@Data
@ApiModel(value = "优惠券查询参数")
public class CouponQueryDto {

    @ApiModelProperty(value = "优惠券名称")
    private String name;

    @ApiModelProperty(value = "优惠券类型")
    private Integer type;

    @ApiModelProperty(value = "使用类型")
    private Integer useType;

    private Integer pageNum;

    private Integer pageSize;
}
