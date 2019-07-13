package com.maxmall.provider.product.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CategoryVo implements Serializable {

    private Long id;

    /**
     * 父类目ID.
     */
    private Long parentId;

    /**
     * 商户Id.
     */
    private Long merchantId;

    /**
     * 图标.
     */
    private String icon;

    /**
     * 类目名称.
     */
    private String name;

    /**
     * 状态.
     */
    private String status;

    /**
     * 关键字.
     */
    private String keywords;

    /**
     * 父类目IDS逗号分割.
     */
    private String parentIds;

    /**
     * 父类目IDS逗号分割.
     */
    private String[] parentIdsList;

    /**
     * 描述.
     */
    private String description;

    /**
     * 排序值.
     */
    private Integer sort;

    /**
     * 层级.
     */
    private Integer level;

    /**
     * 是否叶子节点.
     */
    private Integer isLeaf;

    /**
     * 显示状态.
     */
    private Integer showStatus;

    /**
     * 是否有子类目
     */
    private boolean hasChild;

    /**
     * 孩子列表
     */
    private List<CategoryVo> children;

    /**
     * 类目属性列表
     */
    private List<CategoryAttributeVo> attributeList;

}
