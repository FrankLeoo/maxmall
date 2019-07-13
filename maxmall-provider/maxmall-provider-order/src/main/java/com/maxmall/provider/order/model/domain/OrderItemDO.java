package com.maxmall.provider.order.model.domain;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
import java.math.BigDecimal;

/**
 * The table 订单中所包含的商品
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "oms_order_item")
public class OrderItemDO extends BaseEntity {

    /**
     * 订单id.
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 优惠券id.
     */
    @Column(name = "coupon_id")
    private Long couponId;

    /**
     * 商品id.
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 供应商ID.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 活动id.
     */
    @Column(name = "promotion_id")
    private Long promotionId;

    /**
     * 商品sku编号.
     */
    @Column(name = "product_sku_id")
    private Long productSkuId;

    /**
     * 品牌id.
     */
    @Column(name = "product_brand_id")
    private Long productBrandId;

    /**
     * 商品分类id.
     */
    @Column(name = "product_category_id")
    private Long productCategoryId;

    /**
     * 订单编号.
     */
    @Column(name = "order_sn")
    private String orderSn;

    /**
     * 商品编码.
     */
    @Column(name = "product_sn")
    private String productSn;

    /**
     * 优惠券名称.
     */
    @Column(name = "coupon_name")
    private String couponName;

    /**
     * 商品图片.
     */
    @Column(name = "product_pic")
    private String productPic;

    /**
     * 商品名称.
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 商品促销名称.
     */
    @Column(name = "promotion_name")
    private String promotionName;

    /**
     * 商品的销售属性.
     */
    @Column(name = "sale_attribute")
    private String saleAttribute;

    /**
     * 商品sku条码.
     */
    @Column(name = "product_sku_code")
    private String productSkuCode;

    /**
     * 品牌名称.
     */
    @Column(name = "product_brand_name")
    private String productBrandName;

    /**
     * 商品分类名称.
     */
    @Column(name = "product_category_name")
    private String productCategoryName;

    /**
     * 是否赠品.
     */
    @Column(name = "is_present")
    private Integer isPresent;

    /**
     * 赠送成长值.
     */
    @Column(name = "gift_growth")
    private Integer giftGrowth;

    /**
     * 赠送积分.
     */
    @Column(name = "gift_integration")
    private Integer giftIntegration;

    /**
     * 购买数量.
     */
    @Column(name = "product_quantity")
    private Integer productQuantity;

    /**
     * 该商品经过优惠后的分解金额.
     */
    @Column(name = "real_amount")
    private BigDecimal realAmount;

    /**
     * 优惠券优惠分解金额.
     */
    @Column(name = "coupon_amount")
    private BigDecimal couponAmount;

    /**
     * 销售价格.
     */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /**
     * 商品促销分解金额.
     */
    @Column(name = "promotion_amount")
    private BigDecimal promotionAmount;

    /**
     * 积分优惠分解金额.
     */
    @Column(name = "integration_amount")
    private BigDecimal integrationAmount;

}
