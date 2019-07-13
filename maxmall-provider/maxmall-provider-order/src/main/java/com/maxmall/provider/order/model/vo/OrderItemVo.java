package com.maxmall.provider.order.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The table 订单中所包含的商品
 */
@Data
public class OrderItemVo implements Serializable {

    private Long id;
    /**
     * 订单id.
     */
    private Long orderId;

    /**
     * 优惠券id.
     */
    private Long couponId;

    /**
     * 商品id.
     */
    private Long productId;

    /**
     * 供应商ID.
     */
    private Long merchantId;

    /**
     * 活动id.
     */
    private Long promotionId;

    /**
     * 商品sku编号.
     */
    private Long productSkuId;

    /**
     * 品牌id.
     */
    private Long productBrandId;

    /**
     * 商品分类id.
     */
    private Long productCategoryId;

    /**
     * 订单编号.
     */
    private String orderSn;

    /**
     * 商品编码.
     */
    private String productSn;

    /**
     * 优惠券名称.
     */
    private String couponName;

    /**
     * 商品图片.
     */
    private String productPic;

    /**
     * 商品名称.
     */
    private String productName;

    /**
     * 商品促销名称.
     */
    private String promotionName;

    /**
     * 商品的销售属性.
     */
    private String saleAttribute;

    /**
     * 商品sku条码.
     */
    private String productSkuCode;

    /**
     * 品牌名称.
     */
    private String productBrandName;

    /**
     * 商品分类名称.
     */
    private String productCategoryName;

    /**
     * 是否赠品.
     */
    private Integer isPresent;

    /**
     * 赠送成长值.
     */
    private Integer giftGrowth;

    /**
     * 赠送积分.
     */
    private Integer giftIntegration;

    /**
     * 购买数量.
     */
    private Integer productQuantity;

    /**
     * 该商品经过优惠后的分解金额.
     */
    private BigDecimal realAmount;

    /**
     * 优惠券优惠分解金额.
     */
    private BigDecimal couponAmount;

    /**
     * 销售价格.
     */
    private BigDecimal productPrice;

    /**
     * 商品促销分解金额.
     */
    private BigDecimal promotionAmount;

    /**
     * 积分优惠分解金额.
     */
    private BigDecimal integrationAmount;

}
