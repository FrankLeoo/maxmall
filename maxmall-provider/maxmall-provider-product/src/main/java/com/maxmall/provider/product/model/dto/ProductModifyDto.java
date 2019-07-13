package com.maxmall.provider.product.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品新增参数
 * Created by maxmall on 2018/4/26.
 */
@Data
@ApiModel(value = "商品创建参数")
public class ProductModifyDto {

    private Long id;

    /**
     * 品牌id.
     */
    @ApiModelProperty(value = "品牌",required = true)
    private Long brandId;

    /**
     * 类目id.
     */
    @NotNull
    @ApiModelProperty(value = "类目",required = true)
    private Long categoryId;

    /**
     * 商品主图片.
     */
    @NotEmpty
    @Length(max = 255)
    @ApiModelProperty(value = "商品主图片",required = true)
    private String pic;

    /**
     * 商品名.
     */
    @NotEmpty
    @Length(max = 64)
    @ApiModelProperty(value = "商品名称",required = true)
    private String name;

    /**
     * 单位.
     */
    @NotEmpty
    @Length(max = 64)
    @ApiModelProperty(value = "商品单位",required = true)
    private String unit;

    /**
     * 重量.
     */
    @NotEmpty
    @Length(max = 64)
    @ApiModelProperty(value = "商品重量",required = true)
    private String weight;

    /**
     * 关键字.
     */
    @Length(max = 255)
    @ApiModelProperty(value = "关键字",required = true)
    private String keywords;

    /**
     * 副标题.
     */
    @NotEmpty
    @Length(max = 64)
    @ApiModelProperty(value = "副标题",required = true)
    private String subTitle;

    /**
     * 图片集合.
     */
    @Length(max = 500)
    @ApiModelProperty(value = "商品主图片集合",required = true)
    private String albumPics;

    /**
     * web端描述.
     */
    @NotEmpty
    @Length(max = 1000)
    @ApiModelProperty(value = "web端描述",required = true)
    private String detailDesc;

    /**
     * 描述.
     */
    @Length(max = 500)
    @ApiModelProperty(value = "描述",required = true)
    private String description;

    /**
     * 销售属性（来源于category和自定义）
     [{key:"颜色",value:"红色,褐色"},{key:容量,value:"32G,64G"},{key:内存,value:" 4G,8G"}].
     */
    @Length(max = 500)
    @ApiModelProperty(value = "销售属性",required = true)
    private String productAttrs;

    /**
     * 排序.
     */
    private Integer sort;

    /**
     * 库存.
     */
    @NotNull
    private Integer stock;

    /**
     * 预警库存.
     */
    private Integer lowStock;

    /**
     * 是否热卖.
     */
    private Integer hotStatus;

    /**
     * 是否新品.
     */
    private Integer newStatus;

    /**
     * 是否推荐.
     */
    private Integer recommandStatus;

    /**
     * 是否发布.
     */
    private Integer isPublish;

    /**
     * 交易价格.
     */
    @NotNull
    private BigDecimal price;

    /**
     * 市场价格.
     */
    @NotNull
    private BigDecimal marketPrice;

    /**
     * 商品sku列表
     */
    private List<SkuModifyDto> skuList;

}
