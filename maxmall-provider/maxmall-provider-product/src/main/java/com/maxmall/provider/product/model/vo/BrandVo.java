package com.maxmall.provider.product.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BrandVo implements Serializable {

    private Long id;
    /**
     * 商户Id.
     */
    private Long merchantId;

    /**
     * 品牌logo.
     */
    private String logo;

    /**
     * 品牌名称.
     */
    private String name;

    /**
     * 品牌英文名称.
     */
    private String enName;

    /**
     * 品牌故事.
     */
    private String description;

    /**
     * 首字母.
     */
    private String firstLetter;

    /**
     * 排序.
     */
    private Integer sort;

    /**
     * 状态.
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

}
