package com.maxmall.provider.product.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "品牌查询参数")
public class BrandQueryDto {

    @ApiModelProperty(value = "品牌名称")
    private String name;

    @ApiModelProperty(value = "英文名称")
    private String enName;

    private Integer pageNum;

    private Integer pageSize;
}
