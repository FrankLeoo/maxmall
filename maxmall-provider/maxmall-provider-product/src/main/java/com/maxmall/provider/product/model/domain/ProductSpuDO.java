package com.maxmall.provider.product.model.domain;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
import java.math.BigDecimal;

/**
 * The table 商品spu表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "pms_product_spu")
public class ProductSpuDO extends BaseEntity {

    /**
     * 品牌id.
     */
    @Column(name = "brand_id")
    private Long brandId;

    /**
     * 类目id.
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 商户id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 商品主图片.
     */
    @Column(name = "pic")
    private String pic;

    /**
     * 商品名.
     */
    @Column(name = "name")
    private String name;

    /**
     * 单位.
     */
    @Column(name = "unit")
    private String unit;

    /**
     * 重量.
     */
    @Column(name = "weight")
    private String weight;

    /**
     * 商品编码.
     */
    @Column(name = "spu_code")
    private String spuCode;

    /**
     * 关键字.
     */
    @Column(name = "keywords")
    private String keywords;

    /**
     * 副标题.
     */
    @Column(name = "sub_title")
    private String subTitle;

    /**
     * 图片集合.
     */
    @Column(name = "album_pics")
    private String albumPics;

    /**
     * 品牌名称.
     */
    @Column(name = "brand_name")
    private String brandName;

    /**
     * web端描述.
     */
    @Column(name = "detail_desc")
    private String detailDesc;

    /**
     * 描述.
     */
    @Column(name = "description")
    private String description;

    /**
     * 类目名称.
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 销售属性（来源于category和自定义）
[{key:"颜色",value:"红色,褐色"},{key:容量,value:"32G,64G"},{key:内存,value:" 4G,8G"}].
     */
    @Column(name = "product_attrs")
    private String productAttrs;

    /**
     * 排序.
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 库存.
     */
    @Column(name = "stock")
    private Integer stock;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 销量.
     */
    @Column(name = "sale_num")
    private Integer saleNum;

    /**
     * 预警库存.
     */
    @Column(name = "low_stock")
    private Integer lowStock;

    /**
     * 是否热卖.
     */
    @Column(name = "hot_status")
    private Integer hotStatus;

    /**
     * 是否发布.
     */
    @Column(name = "is_publish")
    private Integer isPublish;

    /**
     * 是否新品.
     */
    @Column(name = "new_status")
    private Integer newStatus;

    /**
     * 是否推荐.
     */
    @Column(name = "recommand_status")
    private Integer recommandStatus;

    /**
     * 交易价格.
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 市场价格.
     */
    @Column(name = "market_price")
    private BigDecimal marketPrice;

}
