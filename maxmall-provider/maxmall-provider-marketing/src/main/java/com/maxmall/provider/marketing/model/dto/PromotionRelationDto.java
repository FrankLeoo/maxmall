package com.maxmall.provider.marketing.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "活动关联参数")
public class PromotionRelationDto implements Serializable {
    /**
     * RELATION_ID.
     */
    @NotNull
    @ApiModelProperty(value = "关联id")
    private Long relationId;

    /**
     * 关联编码.
     */
    @ApiModelProperty(value = "关联编码")
    private String relationSn;

    /**
     * 关联图片.
     */
    @ApiModelProperty(value = "关联图片")
    private String relationPic;

    /**
     * 关联名称.
     */
    @ApiModelProperty(value = "关联名称")
    private String relationName;

    /**
     * 关联类型0:商品 1:分类.
     */
    @NotNull
    @ApiModelProperty(value = "关联类型")
    private Integer type;

}
