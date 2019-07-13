package com.maxmall.provider.product.model.domain;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 商品品牌表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "pms_brand")
public class BrandDO extends BaseEntity {

    /**
     * 商户Id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 品牌logo.
     */
    @Column(name = "logo")
    private String logo;

    /**
     * 品牌名称.
     */
    @Column(name = "name")
    private String name;

    /**
     * 品牌英文名称.
     */
    @Column(name = "en_name")
    private String enName;

    /**
     * 品牌故事.
     */
    @Column(name = "description")
    private String description;

    /**
     * 首字母.
     */
    @Column(name = "first_letter")
    private String firstLetter;

    /**
     * 排序.
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

}
