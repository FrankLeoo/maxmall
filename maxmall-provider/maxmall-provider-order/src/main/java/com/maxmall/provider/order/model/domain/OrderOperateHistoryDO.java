package com.maxmall.provider.order.model.domain;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 订单操作历史记录
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "oms_order_operate_history")
public class OrderOperateHistoryDO extends BaseEntity {

    /**
     * 订单id.
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 供应商ID.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 操作人：用户；系统；后台管理员.
     */
    @Column(name = "handle_man_id")
    private Long handleManId;

    /**
     * 分销商ID.
     */
    @Column(name = "distributor_id")
    private Long distributorId;

    /**
     * 订单编号.
     */
    @Column(name = "order_sn")
    private String orderSn;

    /**
     * 流转描述信息.
     */
    @Column(name = "description")
    private String description;

    /**
     * 处理人员名称.
     */
    @Column(name = "handle_man_name")
    private Integer handleManName;

    /**
     * 新订单状态.
     */
    @Column(name = "new_order_status")
    private Integer newOrderStatus;

    /**
     * 原订单状态.
     */
    @Column(name = "old_order_status")
    private Integer oldOrderStatus;

    /**
     * 操作时间.
     */
    @Column(name = "create_time")
    private Date createTime;

}
