package com.maxmall.provider.merchant.model.domain.merchant;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 分销商销售返点规则表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "mcs_distributor_commission_config")
public class DistrCommissionConfigDO extends BaseEntity {


    /**
     * 返点名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 商户Id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 最大销售额.
     */
    @Column(name = "max_sales")
    private Double maxSales;

    /**
     * 最小销售额.
     */
    @Column(name = "min_sales")
    private Double minSales;

    /**
     * 分销额返点比率.
     */
    @Column(name = "sales_ratio")
    private Double salesRatio;

    /**
     * 描述.
     */
    @Column(name = "description")
    private String description;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

}
