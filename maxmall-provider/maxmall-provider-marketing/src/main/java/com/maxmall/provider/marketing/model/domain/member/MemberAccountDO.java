package com.maxmall.provider.marketing.model.domain.member;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
import java.math.BigDecimal;

/**
 * The table 会员统计账目表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "msc_member_account")
public class MemberAccountDO extends BaseEntity {

    /**
     * 会员ID.
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * MERCHANT_ID.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 分销商ID.
     */
    @Column(name = "distributor_id")
    private Long distributorId;

    /**
     * 会员微信头像.
     */
    @Column(name = "member_icon")
    private String memberIcon;

    /**
     * 会员微信昵称.
     */
    @Column(name = "member_nickname")
    private String memberNickname;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 订单数量.
     */
    @Column(name = "order_count")
    private Integer orderCount;

    /**
     * 优惠券数量.
     */
    @Column(name = "coupon_count")
    private Integer couponCount;

    /**
     * 评价数.
     */
    @Column(name = "comment_count")
    private Integer commentCount;

    /**
     * 退货数量.
     */
    @Column(name = "return_order_count")
    private Integer returnOrderCount;

    /**
     * 累计消费金额.
     */
    @Column(name = "consume_amount")
    private BigDecimal consumeAmount;

}
