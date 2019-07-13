package com.maxmall.provider.marketing.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单查询参数
 * Created by macro on 2018/10/11.
 */
@Data
@ApiModel(value = "活动查询参数")
public class PromotionQueryDto {

    @ApiModelProperty(value = "活动名称")
    private String name;

    @ApiModelProperty(value = "活动类型")
    private Integer type;

    private Integer pageNum;

    private Integer pageSize;
}
