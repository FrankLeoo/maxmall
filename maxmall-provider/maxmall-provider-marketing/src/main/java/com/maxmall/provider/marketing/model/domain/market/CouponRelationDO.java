package com.maxmall.provider.marketing.model.domain.market;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The table 优惠券的关系表
 */
@Data
@Table(name = "sms_coupon_relation")
public class CouponRelationDO implements Serializable {

    /**
     * COUPON_ID.
     */
    @Column(name = "coupon_id")
    private Long couponId;

    /**
     * MERCHANT_ID.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * RELATION_ID.
     */
    @Column(name = "relation_id")
    private Long relationId;

    /**
     * 关联编码.
     */
    @Column(name = "relation_sn")
    private String relationSn;

    /**
     * 关联图片.
     */
    @Column(name = "relation_pic")
    private String relationPic;

    /**
     * 关联名称.
     */
    @Column(name = "relation_name")
    private String relationName;

    /**
     * 关联类型0:商品 1:分类.
     */
    @Column(name = "type")
    private Integer type;

}
