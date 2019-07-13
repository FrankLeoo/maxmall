package com.maxmall.provider.merchant.model.domain.merchant;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 商户会员等级规则表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "mcs_member_level_config")
public class MemberLevelConfigDO extends BaseEntity {

    /**
     * 商户Id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 等级赠送优惠券ID.
     */
    @Column(name = "growth_coupon_id")
    private Long growthCouponId;

    /**
     * 等级名称.
     */
    @Column(name = "name")
    private String name;

    /**
     * 等级描述.
     */
    @Column(name = "description")
    private String description;

    /**
     * 等级赠送优惠券名称.
     */
    @Column(name = "growth_coupon_name")
    private String growthCouponName;

    /**
     * 等级.
     */
    @Column(name = "level")
    private Integer level;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 需要积分.
     */
    @Column(name = "growth_point")
    private Integer growthPoint;

}
