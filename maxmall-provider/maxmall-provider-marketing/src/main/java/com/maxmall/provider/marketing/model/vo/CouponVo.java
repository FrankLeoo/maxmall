package com.maxmall.provider.marketing.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class CouponVo implements Serializable {

    private Long id;

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
     * 备注.
     */
    private String description;

    /**
     * 优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券.
     */
    private Integer type;

    /**
     * 状态.
     */
    private Integer status;

    /**
     * 使用类型：0->全场通用；1->指定分类；2->指定商品.
     */
    private Integer useType;

    /**
     * 领取后几天.
     */
    private Integer afterDay;

    /**
     * 每人限领张数.
     */
    private Integer perLimit;

    /**
     * 已使用数量.
     */
    private Integer useCount;

    /**
     * 使用计时方式（0:固定时间；1:领取后几天）.
     */
    private Integer startType;

    /**
     * 发行数量.
     */
    private Integer publishCount;

    /**
     * 领取数量.
     */
    private Integer receiveCount;

    /**
     * 使用结束时间.
     */
    private Date endTime;

    /**
     * 使用开始时间.
     */
    private Date startTime;

    /**
     * 可以领取的日期.
     */
    private Date enableTime;

    /**
     * 金额.
     */
    private BigDecimal amount;

    /**
     * 使用门槛；0表示无门槛.
     */
    private BigDecimal minPoint;

    private Date createTime;

    /**
     * 关联关系列表
     */
    private List<CouponRelationVo> relationList;
}
