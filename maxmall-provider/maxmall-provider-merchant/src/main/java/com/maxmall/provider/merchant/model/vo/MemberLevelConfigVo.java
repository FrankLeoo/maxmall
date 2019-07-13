package com.maxmall.provider.merchant.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MemberLevelConfigVo implements Serializable {

    private Long id;
    /**
     * 商户Id.
     */
    private Long merchantId;

    /**
     * 等级赠送优惠券ID.
     */
    private Long growthCouponId;

    /**
     * 等级名称.
     */
    private String name;

    /**
     * 等级描述.
     */
    private String description;

    /**
     * 等级赠送优惠券名称.
     */
    private String growthCouponName;

    /**
     * 等级.
     */
    private Integer level;

    /**
     * 状态.
     */
    private Integer status;

    /**
     * 需要积分.
     */
    private Integer growthPoint;

    private Date createTime;
}
