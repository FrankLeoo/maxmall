package com.maxmall.provider.merchant.model.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class DisCommissionConfigVo implements Serializable {

    private Long id;

    /**
     * 商户Id.
     */
    private Long merchantId;

    /**
     * 返点名称
     */
    private String name;

    /**
     * 最大销售额.
     */
    private Double maxSales;

    /**
     * 最小销售额.
     */
    private Double minSales;

    /**
     * 分销额返点比率.
     */
    private Double salesRatio;

    /**
     * 描述.
     */
    private String description;

    /**
     * 状态.
     */
    private Integer status;
}
