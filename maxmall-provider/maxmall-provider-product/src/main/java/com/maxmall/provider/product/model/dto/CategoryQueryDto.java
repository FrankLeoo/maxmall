package com.maxmall.provider.product.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分类查询参数")
public class CategoryQueryDto {

    @ApiModelProperty(value = "分类名称")
    private String name;
    @ApiModelProperty(value = "父分类")
    private Long parentId;

    private Integer pageNum;

    private Integer pageSize;
}
