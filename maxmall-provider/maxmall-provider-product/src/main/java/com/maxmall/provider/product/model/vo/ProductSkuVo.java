package com.maxmall.provider.product.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductSkuVo implements Serializable {

    private Long id;
    /**
     * spuid.
     */
    private Long spuId;

    /**
     * 商户id.
     */
    private Long merchantId;

    /**
     * 商品主图.
     */
    private String pic;

    /**
     * spu编码.
     */
    private String skuCode;

    /**
     * 销售属性.
     */
    private String skuAttrs;

    /**
     * 商品图集.
     */
    private String albumPics;

    /**
     * 库存.
     */
    private Integer stock;

    /**
     * 状态.
     */
    private Integer status;

    /**
     * 销售价格.
     */
    private Integer saleNum;

    /**
     * 预警库存.
     */
    private Integer lowStock;

    /**
     * 锁定库存.
     */
    private Integer lockStock;

    /**
     * 销售价格.
     */
    private BigDecimal price;
}
