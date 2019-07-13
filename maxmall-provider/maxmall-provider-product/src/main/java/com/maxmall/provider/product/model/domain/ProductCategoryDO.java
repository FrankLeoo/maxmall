package com.maxmall.provider.product.model.domain;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 商品分类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "pms_product_category")
public class ProductCategoryDO extends BaseEntity {

    /**
     * 父类目ID.
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 商户Id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 图标.
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 类目名称.
     */
    @Column(name = "name")
    private String name;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 关键字.
     */
    @Column(name = "keywords")
    private String keywords;

    /**
     * 父类目IDS逗号分割.
     */
    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 描述.
     */
    @Column(name = "description")
    private String description;

    /**
     * 排序值.
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 层级.
     */
    @Column(name = "level")
    private Integer level;

    /**
     * 是否叶子节点.
     */
    @Column(name = "is_leaf")
    private Integer isLeaf;

    /**
     * 显示状态.
     */
    @Column(name = "show_status")
    private Integer showStatus;

}
