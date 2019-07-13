package com.maxmall.provider.merchant.model.dto.distributor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "分销商分销比率Dto ")
public class DistributorRadioDto implements Serializable {

    /**
     * 分销商ID.
     */
    @NotNull
    @ApiModelProperty(value = "分销商ID")
    private Long id;

    /**
     * 本级销售分销比率.
     */
    @NotNull
    @ApiModelProperty(value = "本级销售分销比率")
    private Double selfSalesRatio;

    /**
     * 代理分销佣金.
     */
    @NotNull
    @ApiModelProperty(value = "代理分销佣金")
    private Double agentCommission;

    /**
     * 祖父级销售分销比率.
     */
    @NotNull
    @ApiModelProperty(value = "祖父级销售分销比率")
    private Double grandSalesRatio;
}
