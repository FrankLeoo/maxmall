package com.maxmall.provider.order.model.domain;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * The table 退货原因表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "oms_order_return_reason")
public class OrderReturnReasonDO extends BaseEntity {

    /**
     * 商户id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 退货类型.
     */
    @Column(name = "name")
    private String name;

    /**
     * SORT.
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 状态：0->不启用；1->启用.
     */
    @Column(name = "status")
    private Integer status;

}
