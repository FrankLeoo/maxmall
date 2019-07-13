package com.maxmall.provider.product.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "商品全部查询")
public class ProductListQueryDto {

    @ApiModelProperty(value = "名称或编码")
    private String keyWords;

    /**
     * 品牌id.
     */
    @ApiModelProperty(value = "品牌")
    private Long brandId;

    /**
     * 类目id.
     */
    @ApiModelProperty(value = "类目")
    private Long categoryId;

    @ApiModelProperty(value = "商品名称")
    private String name;

    /**
     * 商品编码.
     */
    @ApiModelProperty(value = "商品编码")
    private String spuCode;
}
