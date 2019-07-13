package com.maxmall.provider.merchant.model.dto.merchant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "分销商等级配置添加")
public class DisLevelModifyDto implements Serializable {
    private Long id;

    @ApiModelProperty(value = "等级名称",required = true)
    @NotEmpty(message = "名称不能为空")
    @Length(max = 64)
    private String name;

    /**
     * 等级描述.
     */
    @ApiModelProperty(value = "等级描述")
    @Length(max = 255)
    private String description;

    /**
     * 本级销售分销比率.
     */
    @ApiModelProperty(value = "本级销售分销比率")
    private Double selfSalesRatio;

    /**
     * 祖父级销售分销比率.
     */
    @ApiModelProperty(value = "祖父级销售分销比率")
    private Double grandSalesRatio;

    /**
     * 父级销售分销比率.
     */
    @ApiModelProperty(value = "父级销售分销比率")
    private Double parentSalesRatio;

    /**
     * 是否需要会费 0:否 1:是.
     */
    @ApiModelProperty(value = "是否需要会费 0:否 1:是")
    private Integer isDues;
    /**
     * 需要会费.
     */
    @ApiModelProperty(value = "需要会费")
    private Double duesPoint;

    /**
     * 邀请人会费分销比率.
     */
    @ApiModelProperty(value = "邀请人会费分销比率")
    private Double selfDuesRatio;

    /**
     * 邀请人祖父级会费分销比率.
     */
    @ApiModelProperty(value = "邀请人祖父级会费分销比率")
    private Double grandDuesRatio;

    /**
     * 邀请人父级会费分销比率.
     */
    @ApiModelProperty(value = "邀请人父级会费分销比率")
    private Double parentDuesRatio;


}
