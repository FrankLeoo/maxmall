package com.maxmall.provider.merchant.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DistributorLevelConfigVo implements Serializable {

    private Long id;
    /**
     * 商户Id.
     */
    private Long merchantId;

    /**
     * 需要会费.
     */
    private Double duesPoint;

    /**
     * 邀请人会费分销比率.
     */
    private Double selfDuesRatio;

    /**
     * 邀请人祖父级会费分销比率.
     */
    private Double grandDuesRatio;

    /**
     * 本级销售分销比率.
     */
    private Double selfSalesRatio;

    /**
     * 祖父级销售分销比率.
     */
    private Double grandSalesRatio;

    /**
     * 邀请人父级会费分销比率.
     */
    private Double parentDuesRatio;

    /**
     * 父级销售分销比率.
     */
    private Double parentSalesRatio;

    /**
     * 等级名称.
     */
    private String name;

    /**
     * 等级描述.
     */
    private String description;

    /**
     * 是否需要会费 0:否 1:是.
     */
    private Integer isDues;

    /**
     * 状态.
     */
    private Integer status;

    private Date createTime;
}
