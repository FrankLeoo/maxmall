package com.maxmall.provider.marketing.model.domain.market;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 活动开始通知表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "sms_promotion_notify")
public class PromotionNotifyDO extends BaseEntity {

    /**
     * 会员id.
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * 商品id.
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 活动id.
     */
    @Column(name = "promotion_id")
    private Long promotionId;

    /**
     * 会员头像.
     */
    @Column(name = "member_icon")
    private String memberIcon;

    /**
     * 会员名称.
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 商品图片.
     */
    @Column(name = "product_pic")
    private String productPic;

    /**
     * 会员手机号.
     */
    @Column(name = "member_phone")
    private String memberPhone;

    /**
     * 商品名称.
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 活动名称.
     */
    @Column(name = "promotion_name")
    private String promotionName;

    /**
     * 是否发送 0:否 1:是.
     */
    @Column(name = "is_seand")
    private Integer isSeand;

    /**
     * 发送时间.
     */
    @Column(name = "send_time")
    private Date sendTime;

    /**
     * 订阅时间.
     */
    @Column(name = "subscribe_time")
    private Date subscribeTime;

}
