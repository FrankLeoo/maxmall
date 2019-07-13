package com.maxmall.provider.marketing.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CouponRelationVo implements Serializable {

    private Long id;
    /**
     * COUPON_ID.
     */
    private Long couponId;

    /**
     * MERCHANT_ID.
     */
    private Long merchantId;

    /**
     * RELATION_ID.
     */
    private Long relationId;

    /**
     * 关联编码.
     */
    private String relationSn;

    /**
     * 关联图片.
     */
    private String relationPic;

    /**
     * 关联名称.
     */
    private String relationName;

    /**
     * 关联类型0:商品 1:分类.
     */
    private Integer type;

}
