package com.maxmall.provider.marketing.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MemberCouponVo implements Serializable {

    private Long id;

    /**
     * 优惠券id.
     */
    private Long couponId;

    /**
     * C端用户会员id.
     */
    private Long memberId;

    /**
     * MERCHANT_ID.
     */
    private Long merchantId;

    /**
     * 优惠码.
     */
    private String code;

    /**
     * NAME.
     */
    private String name;

    /**
     * 用户头像.
     */
    private String memberIcon;

    /**
     * 用户帐号.
     */
    private String memberName;

    /**
     * 使用source（例如：订单id）.
     */
    private String usedSource;

    /**
     * 备注.
     */
    private String description;

    /**
     * 优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券.
     */
    private Integer type;

    /**
     * 数量.
     */
    private Integer count;

    /**
     * 是否使用.
     */
    private Integer isUsed;

    /**
     * 状态.
     */
    private Integer status;

    /**
     * 使用类型：0->全场通用；1->指定分类；2->指定商品.
     */
    private Integer useType;

    /**
     * 使用类型（1:订单 2:赠送）.
     */
    private Integer usedSourceType;

    /**
     * 使用结束时间.
     */
    private Date endTime;

    /**
     * 使用时间.
     */
    private Date usedTime;

    /**
     * 使用开始时间.
     */
    private Date startTime;

    /**
     * 金额.
     */
    private BigDecimal amount;

    /**
     * 使用门槛；0表示无门槛.
     */
    private BigDecimal minPoint;
}
