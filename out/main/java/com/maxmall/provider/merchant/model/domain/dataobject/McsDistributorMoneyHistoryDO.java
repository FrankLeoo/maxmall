package com.maxmall.provider.merchant.model.domain.dataobject;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 分销商操作记录表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "mcs_distributor_money_history")
public class McsDistributorMoneyHistoryDO extends BaseEntity {

    /**
     * 订单id.
     */
    @Column(name = "source_id")
    private Long sourceId;

    /**
     * 商户Id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 分销商id.
     */
    @Column(name = "distributor_id")
    private Long distributorId;

    /**
     * 操作员id.
     */
    @Column(name = "handler_user_id")
    private Long handlerUserId;

    /**
     * 操作金额.
     */
    @Column(name = "money")
    private Double money;

    /**
     * 操作记录.
     */
    @Column(name = "history")
    private String history;

    /**
     * 操作员头像.
     */
    @Column(name = "handler_user_pic")
    private String handlerUserPic;

    /**
     * 操作员名称.
     */
    @Column(name = "handler_user_name")
    private String handlerUserName;

    /**
     * 操作类型 0:分销 1:返点 2:提现.
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

}
