package com.maxmall.provider.marketing.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class PromotionVo implements Serializable {
    private Long id;
    /**
     * 赠品id.
     */
    private Long giftId;

    /**
     * MERCHANT_ID.
     */
    private Long merchantId;

    /**
     * 使用类型：0->全场通用；1->指定分类；2->指定商品.
     */
    private Integer useType;

    /**
     * 活动名称.
     */
    private String name;

    /**
     * 赠品图片.
     */
    private String giftPic;

    /**
     * 赠品名称.
     */
    private String giftName;

    /**
     * 活动类型；0->满减；1->满赠；2->限时优惠；3->赠品.
     */
    private Integer type;

    /**
     * 上下线状态.
     */
    private Integer status;

    /**
     * 限时折扣.
     */
    private Integer discount;

    /**
     * 赠品类型（0:商品 1:优惠券）.
     */
    private Integer giftType;

    /**
     * 结束日期.
     */
    private Date endTime;

    /**
     * 开始日期.
     */
    private Date startTime;

    /**
     * 满金额.
     */
    private BigDecimal fullPrice;

    /**
     * 减金额.
     */
    private BigDecimal reducePrice;

    private String description;

    /**
     * 活动关联
     */
    private List<PromotionRelationVo> relationList;
}
