package com.maxmall.provider.product.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 商品sku新增参数
 * Created by maxmall on 2018/4/26.
 */
@Data
@ApiModel(value = "商品sku创建参数")
public class SkuModifyDto {
    /**
     * 商品主图.
     */
    @Length(max = 255)
    @ApiModelProperty(value = "商品主图片",required = true)
    private String pic;

    /**
     * 销售属性.
     */
    @Length(max = 500)
    @ApiModelProperty(value = "销售属性",required = true)
    private String skuAttrs;

    /**
     * 商品图集.
     */
    @Length(max = 500)
    @ApiModelProperty(value = "商品图集",required = true)
    private String albumPics;

    /**
     * 库存.
     */
    @NotNull
    @ApiModelProperty(value = "库存",required = true)
    private Integer stock;

    /**
     * 预警库存.
     */
    @ApiModelProperty(value = "预警库存",required = true)
    private Integer lowStock;

    /**
     * 销售价格.
     */
    @NotNull
    @ApiModelProperty(value = "销售价格",required = true)
    private BigDecimal price;
}
