package com.maxmall.provider.marketing.model.domain.market;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
import java.math.BigDecimal;

/**
 * The table 会员优惠卷表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "sms_member_coupon")
public class MemberCouponDO extends BaseEntity {

    /**
     * 优惠券id.
     */
    @Column(name = "coupon_id")
    private Long couponId;

    /**
     * C端用户会员id.
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * MERCHANT_ID.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 优惠码.
     */
    @Column(name = "code")
    private String code;

    /**
     * NAME.
     */
    @Column(name = "name")
    private String name;

    /**
     * 用户头像.
     */
    @Column(name = "member_icon")
    private String memberIcon;

    /**
     * 用户帐号.
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 使用source（例如：订单id）.
     */
    @Column(name = "used_source")
    private String usedSource;

    /**
     * 备注.
     */
    @Column(name = "description")
    private String description;

    /**
     * 优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券.
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 是否使用.
     */
    @Column(name = "is_used")
    private Integer isUsed;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 使用类型：0->全场通用；1->指定分类；2->指定商品.
     */
    @Column(name = "use_type")
    private Integer useType;

    /**
     * 使用类型（1:订单 2:赠送）.
     */
    @Column(name = "used_source_type")
    private Integer usedSourceType;

    /**
     * 使用结束时间.
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 使用时间.
     */
    @Column(name = "used_time")
    private Date usedTime;

    /**
     * 使用开始时间.
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 金额.
     */
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * 使用门槛；0表示无门槛.
     */
    @Column(name = "min_point")
    private BigDecimal minPoint;

}
