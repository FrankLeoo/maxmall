package com.maxmall.provider.product.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductVo implements Serializable {

    private Long id;
    /**
     * 品牌id.
     */
    private Long brandId;

    /**
     * 类目id.
     */
    private Long categoryId;

    /**
     * 商户id.
     */
    private Long merchantId;

    /**
     * 商品主图片.
     */
    private String pic;

    /**
     * 商品名.
     */
    private String name;

    /**
     * 单位.
     */
    private String unit;

    /**
     * 重量.
     */
    private String weight;

    /**
     * 商品编码.
     */
    private String spuCode;

    /**
     * 关键字.
     */
    private String keywords;

    /**
     * 副标题.
     */
    private String subTitle;

    /**
     * 图片集合.
     */
    private String albumPics;

    /**
     * 品牌名称.
     */
    private String brandName;

    /**
     * web端描述.
     */
    private String detailDesc;

    /**
     * 描述.
     */
    private String description;

    /**
     * 类目名称.
     */
    private String categoryName;

    /**
     * 销售属性（来源于category和自定义）
     [{key:"颜色",value:"红色,褐色"},{key:容量,value:"32G,64G"},{key:内存,value:" 4G,8G"}].
     */
    private String productAttrs;

    /**
     * 排序.
     */
    private Integer sort;

    /**
     * 库存.
     */
    private Integer stock;

    /**
     * 状态.
     */
    private Integer status;

    /**
     * 销量.
     */
    private Integer saleNum;

    /**
     * 预警库存.
     */
    private Integer lowStock;

    /**
     * 是否热卖.
     */
    private Integer hotStatus;

    /**
     * 是否发布.
     */
    private Integer isPublish;

    /**
     * 是否新品.
     */
    private Integer newStatus;

    /**
     * 是否推荐.
     */
    private Integer recommandStatus;

    /**
     * 交易价格.
     */
    private BigDecimal price;

    /**
     * 市场价格.
     */
    private BigDecimal marketPrice;

    /**
     * 商品sku列表
     */
    private List<ProductSkuVo> skuList;
}
