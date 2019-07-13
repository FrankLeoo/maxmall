package com.maxmall.provider.marketing.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberAddressVo implements Serializable {

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
     * 省份/直辖市.
     */
    private String receiverCity;

    /**
     * 收货人姓名.
     */
    private String receiverName;

    /**
     * 收货人电话.
     */
    private String receiverPhone;

    /**
     * 会员微信昵称.
     */
    private String memberNickname;

    /**
     * 区.
     */
    private String receiverRegion;

    /**
     * 优惠券数量.
     */
    private String receiverPostCode;

    /**
     * 收货人邮编.
     */
    private String receiverProvince;

    /**
     * 详细地址.
     */
    private String receiverDetailAddress;

    /**
     * 状态.
     */
    private Integer status;

}
