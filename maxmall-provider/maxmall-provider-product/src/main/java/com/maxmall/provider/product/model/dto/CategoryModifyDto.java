package com.maxmall.provider.product.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 品牌传递参数
 * Created by maxmall on 2018/4/26.
 */
@Data
@ApiModel(value = "分类创建参数")
public class CategoryModifyDto {

    private Long id;

    @ApiModelProperty(value = "分类描述")
    @Length(max = 500)
    private String description;

    /**
     * 父类目ID.
     */
    @ApiModelProperty(value = "父类目Id")
    private Long parentId;

    /**
     * 图标.
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 类目名称.
     */
    @ApiModelProperty(value = "类目名称")
    private String name;

    /**
     * 关键字.
     */
    @ApiModelProperty(value = "关键字")
    private String keywords;

    /**
     * 排序值.
     */
    @ApiModelProperty(value = "排序值")
    private Integer sort;

    /**
     * 是否叶子节点.
     */
    @ApiModelProperty(value = "是否叶子节点")
    private Integer isLeaf;

    /**
     * 显示状态.
     */
    @ApiModelProperty(value = "显示状态")
    private Integer showStatus;

    /**
     * 属性列表
     */
    private List<CategoryAttributeModifyDto> attributeList;
}
