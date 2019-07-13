package com.maxmall.provider.marketing.model.domain.member;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 会员收货地址表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "msc_member_address")
public class MemberAddressDO extends BaseEntity {

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
     * 省份/直辖市.
     */
    @Column(name = "receiver_city")
    private String receiverCity;

    /**
     * 收货人姓名.
     */
    @Column(name = "receiver_name")
    private String receiverName;

    /**
     * 收货人电话.
     */
    @Column(name = "receiver_phone")
    private String receiverPhone;

    /**
     * 会员微信昵称.
     */
    @Column(name = "member_nickname")
    private String memberNickname;

    /**
     * 区.
     */
    @Column(name = "receiver_region")
    private String receiverRegion;

    /**
     * 优惠券数量.
     */
    @Column(name = "receiver_post_code")
    private String receiverPostCode;

    /**
     * 收货人邮编.
     */
    @Column(name = "receiver_province")
    private String receiverProvince;

    /**
     * 详细地址.
     */
    @Column(name = "receiver_detail_address")
    private String receiverDetailAddress;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

}
