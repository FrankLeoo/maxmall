package com.maxmall.provider.product.model.domain;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 类目销售属性表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "pms_category_attribute")
public class CategoryAttributeDO extends BaseEntity {

    /**
     * 分类ID.
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 商户Id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 名称 "颜色".
     */
    @Column(name = "name")
    private String name;

    /**
     * 属性项目"红色,褐色".
     */
    @Column(name = "attr_values")
    private String attrValues;

    /**
     * 排序.
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 属性类型0:普通 1:有单独pic.
     */
    @Column(name = "attr_type")
    private Integer attrType;

}
