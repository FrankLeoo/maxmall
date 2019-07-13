package com.maxmall.provider.marketing.model.domain.market;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
import java.math.BigDecimal;

/**
 * The table 优惠卷表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "sms_coupon")
public class CouponDO extends BaseEntity {

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
     * 领取后几天.
     */
    @Column(name = "after_day")
    private Integer afterDay;

    /**
     * 每人限领张数.
     */
    @Column(name = "per_limit")
    private Integer perLimit;

    /**
     * 已使用数量.
     */
    @Column(name = "use_count")
    private Integer useCount;

    /**
     * 使用计时方式（0:固定时间；1:领取后几天）.
     */
    @Column(name = "start_type")
    private Integer startType;

    /**
     * 发行数量.
     */
    @Column(name = "publish_count")
    private Integer publishCount;

    /**
     * 领取数量.
     */
    @Column(name = "receive_count")
    private Integer receiveCount;

    /**
     * 使用结束时间.
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 使用开始时间.
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 可以领取的日期.
     */
    @Column(name = "enable_time")
    private Date enableTime;

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
