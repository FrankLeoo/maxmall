package com.maxmall.provider.marketing.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "会员成长值查询参数")
public class MemberGrowthQueryDto {

    @ApiModelProperty(value = "会员id")
    private Long memberId;

    private Integer pageNum;

    private Integer pageSize;
}
