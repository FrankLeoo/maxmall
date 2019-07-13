package com.maxmall.provider.marketing.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MemberAccountVo implements Serializable {

    private Long id;

    /**
     * 会员ID.
     */
    private Long memberId;

    /**
     * MERCHANT_ID.
     */
    private Long merchantId;

    /**
     * 分销商ID.
     */
    private Long distributorId;

    /**
     * 会员微信头像.
     */
    private String memberIcon;

    /**
     * 会员微信昵称.
     */
    private String memberNickname;

    /**
     * 状态.
     */
    private Integer status;

    /**
     * 订单数量.
     */
    private Integer orderCount;

    /**
     * 优惠券数量.
     */
    private Integer couponCount;

    /**
     * 评价数.
     */
    private Integer commentCount;

    /**
     * 退货数量.
     */
    private Integer returnOrderCount;

    /**
     * 累计消费金额.
     */
    private BigDecimal consumeAmount;

}
