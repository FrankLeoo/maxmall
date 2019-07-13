package com.maxmall.provider.merchant.model.domain.merchant;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 分销商等级规则表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "mcs_distributor_level_config")
public class DistributorLevelConfigDO extends BaseEntity {

    /**
     * 商户Id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 需要会费.
     */
    @Column(name = "dues_point")
    private Double duesPoint;

    /**
     * 邀请人会费分销比率.
     */
    @Column(name = "self_dues_ratio")
    private Double selfDuesRatio;

    /**
     * 邀请人祖父级会费分销比率.
     */
    @Column(name = "grand_dues_ratio")
    private Double grandDuesRatio;

    /**
     * 本级销售分销比率.
     */
    @Column(name = "self_sales_ratio")
    private Double selfSalesRatio;

    /**
     * 祖父级销售分销比率.
     */
    @Column(name = "grand_sales_ratio")
    private Double grandSalesRatio;

    /**
     * 邀请人父级会费分销比率.
     */
    @Column(name = "parent_dues_ratio")
    private Double parentDuesRatio;

    /**
     * 父级销售分销比率.
     */
    @Column(name = "parent_sales_ratio")
    private Double parentSalesRatio;

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
     * 是否需要会费 0:否 1:是.
     */
    @Column(name = "is_dues")
    private Integer isDues;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

}
