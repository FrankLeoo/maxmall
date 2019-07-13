package com.maxmall.provider.marketing.model.domain.market;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
import java.math.BigDecimal;

/**
 * The table 活动信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "sms_promotion")
public class PromotionDO extends BaseEntity {

    /**
     * 赠品id.
     */
    @Column(name = "gift_id")
    private Long giftId;

    /**
     * MERCHANT_ID.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 活动名称.
     */
    @Column(name = "name")
    private String name;

    /**
     * 赠品图片.
     */
    @Column(name = "gift_pic")
    private String giftPic;

    /**
     * 使用类型：0->全场通用；1->指定分类；2->指定商品.
     */
    @Column(name = "use_type")
    private Integer useType;

    /**
     * 赠品名称.
     */
    @Column(name = "gift_name")
    private String giftName;

    /**
     * 活动类型；0->满减；1->满赠；2->限时优惠；3->赠品.
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 上下线状态.
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 限时折扣.
     */
    @Column(name = "discount")
    private Integer discount;

    /**
     * 赠品类型（0:商品 1:优惠券）.
     */
    @Column(name = "gift_type")
    private Integer giftType;

    /**
     * 结束日期.
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 开始日期.
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 满金额.
     */
    @Column(name = "full_price")
    private BigDecimal fullPrice;

    /**
     * 减金额.
     */
    @Column(name = "reduce_price")
    private BigDecimal reducePrice;

    /**
     * 备注.
     */
    @Column(name = "description")
    private String description;

}
