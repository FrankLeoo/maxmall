package com.maxmall.provider.product.model.domain;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
import java.math.BigDecimal;

/**
 * The table 商品sku
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "pms_product_sku")
public class ProductSkuDO extends BaseEntity {

    /**
     * spuid.
     */
    @Column(name = "spu_id")
    private Long spuId;

    /**
     * 商户id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 商品主图.
     */
    @Column(name = "pic")
    private String pic;

    /**
     * spu编码.
     */
    @Column(name = "sku_code")
    private String skuCode;

    /**
     * 销售属性.
     */
    @Column(name = "sku_attrs")
    private String skuAttrs;

    /**
     * 商品图集.
     */
    @Column(name = "album_pics")
    private String albumPics;

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
     * 销售价格.
     */
    @Column(name = "sale_num")
    private Integer saleNum;

    /**
     * 预警库存.
     */
    @Column(name = "low_stock")
    private Integer lowStock;

    /**
     * 锁定库存.
     */
    @Column(name = "lock_stock")
    private Integer lockStock;

    /**
     * 销售价格.
     */
    @Column(name = "price")
    private BigDecimal price;

}
