package com.maxmall.provider.product.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "分类销售属性参数")
public class CategoryAttributeModifyDto {

    /**
     * 名称 "颜色".
     */
    @NotNull
    @ApiModelProperty(value = "属性名称")
    private String name;

    /**
     * 属性项目"红色,褐色".
     */
    @NotNull
    @ApiModelProperty(value = "属性项目")
    private String attrValues;

    /**
     * 排序.
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 属性类型0:普通 1:有单独pic.
     */
    @NotNull
    @ApiModelProperty(value = "属性类型")
    private Integer attrType;

}
