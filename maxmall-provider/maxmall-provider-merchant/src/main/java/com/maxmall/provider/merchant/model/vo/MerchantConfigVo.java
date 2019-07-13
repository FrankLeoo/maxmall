package com.maxmall.provider.merchant.model.vo;

import com.maxmall.common.base.dto.BaseVo;
import lombok.Data;

@Data
public class MerchantConfigVo extends BaseVo {

    /**
     * 商户Id.
     */
    private Long merchantId;

    /**
     * 商户编号.
     */
    private String merchantSn;

    /**
     * 支付账号.
     */
    private String payAccount;

    /**
     * 是否开启积分属性.
     */
    private Boolean isPoint;

    /**
     * 是否开启会员成长.
     */
    private Boolean isGrowth;

    /**
     * 是否开启分销.
     */
    private Boolean isDistributor;

    /**
     * 是否销售额返点
     */
    private Boolean isCommission;

    /**
     * 状态.
     */
    private Integer status;

    /**
     * 支付类型 个人|商户.
     */
    private Integer payType;

    /**
     * 积分兑换1元.
     */
    private Integer pointCharge;

    /**
     * 消费1元=多少成长值.
     */
    private Integer growthCharge;

    /**
     * 积分使用限制（单最高抵用百分比）.
     */
    private Integer usePointLimit;

    /**
     * 自动完成交易时间，不能申请售后（天）默认30天.
     */
    private Integer finishOvertime;

    /**
     * 订单完成后自动好评时间（天）默认7天.
     */
    private Integer commentOvertime;

    /**
     * 发货后自动确认收货时间（天） 默认3天.
     */
    private Integer confirmOvertime;

    /**
     * 秒杀订单超时关闭时间(分) 默认10分钟.
     */
    private Integer flashOrderOvertime;

    /**
     * 正常订单超时时间(分) 默认15分钟.
     */
    private Integer normalOrderOvertime;
}
