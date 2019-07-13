package com.maxmall.provider.merchant.model.dto.merchant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "会员等级配置查询")
public class MemberLevelQueryDto implements Serializable {

    @ApiModelProperty(value = "等级名称")
    private String name;

    @ApiModelProperty(value = "会员等级")
    private Integer level;

    private Integer pageNum;

    private Integer pageSize;
}
