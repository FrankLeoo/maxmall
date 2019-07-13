package com.maxmall.provider.merchant.model.dto.merchant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@ApiModel(value = "分销商返点配置添加")
public class DisCommissionModifyDto implements Serializable {
    private Long id;

    @ApiModelProperty(value = "等级名称",required = true)
    @NotEmpty(message = "名称不能为空")
    @Length(max = 64)
    private String name;

    /**
     * 最大销售额.
     */
    @ApiModelProperty(value = "最大销售额",required = true)
    @NotNull(message = "最大销售额不能为空")
    private Double maxSales;

    /**
     * 最小销售额.
     */
    @ApiModelProperty(value = "最小销售额",required = true)
    @NotNull(message = "最小销售额不能为空")
    private Double minSales;

    /**
     * 分销额返点比率.
     */
    @ApiModelProperty(value = "分销额返点比率")
    private Double salesRatio;

    /**
     * 描述.
     */
    @ApiModelProperty(value = "描述")
    private String description;

}
