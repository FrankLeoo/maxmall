package com.maxmall.provider.marketing.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "会员查询参数")
public class MemberQueryDto {

    @ApiModelProperty(value = "会员名称")
    private String nickname;

    private Integer pageNum;

    private Integer pageSize;
}
