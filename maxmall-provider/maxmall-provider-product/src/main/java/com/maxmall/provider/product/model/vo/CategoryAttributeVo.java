package com.maxmall.provider.product.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryAttributeVo implements Serializable {

    private Long id;

    /**
     * 分类ID.
     */
    private Long categoryId;

    /**
     * 商户Id.
     */
    private Long merchantId;

    /**
     * 名称 "颜色".
     */
    private String name;

    /**
     * 属性项目"红色,褐色".
     */
    private String attrValues;

    /**
     * 排序.
     */
    private Integer sort;

    /**
     * 属性类型0:普通 1:有单独pic.
     */
    private Integer attrType;
}
