package com.maxmall.provider.merchant.model.domain.merchant;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 商户配置表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "mcs_merchant_config")
public class MerchantConfigDO extends BaseEntity {

    /**
     * 商户Id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 商户编号.
     */
    @Column(name = "merchant_sn")
    private String merchantSn;

    /**
     * 支付账号.
     */
    @Column(name = "pay_account")
    private String payAccount;

    /**
     * 是否开启积分属性.
     */
    @Column(name = "is_point")
    private Boolean isPoint;

    /**
     * 是否开启会员成长.
     */
    @Column(name = "is_growth")
    private Boolean isGrowth;

    /**
     * 是否开启分销.
     */
    @Column(name = "is_distributor")
    private Boolean isDistributor;

    /**
     * 是否开启销售额返点.
     */
    @Column(name = "is_commission")
    private Boolean isCommission;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 支付类型 个人|商户.
     */
    @Column(name = "pay_type")
    private Integer payType;

    /**
     * 积分兑换1元.
     */
    @Column(name = "point_charge")
    private Integer pointCharge;

    /**
     * 消费1元=多少成长值.
     */
    @Column(name = "growth_charge")
    private Integer growthCharge;

    /**
     * 积分使用限制（单最高抵用百分比）.
     */
    @Column(name = "use_point_limit")
    private Integer usePointLimit;

    /**
     * 自动完成交易时间，不能申请售后（天）默认30天.
     */
    @Column(name = "finish_overtime")
    private Integer finishOvertime;

    /**
     * 订单完成后自动好评时间（天）默认7天.
     */
    @Column(name = "comment_overtime")
    private Integer commentOvertime;

    /**
     * 发货后自动确认收货时间（天） 默认3天.
     */
    @Column(name = "confirm_overtime")
    private Integer confirmOvertime;

    /**
     * 秒杀订单超时关闭时间(分) 默认10分钟.
     */
    @Column(name = "flash_order_overtime")
    private Integer flashOrderOvertime;

    /**
     * 正常订单超时时间(分) 默认15分钟.
     */
    @Column(name = "normal_order_overtime")
    private Integer normalOrderOvertime;

}
