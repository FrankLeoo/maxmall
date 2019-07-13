package com.maxmall.provider.merchant.model.dto.distributor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "分销商分页查询Dto ")
public class ModifyDistributorDto implements Serializable {

    /**
     * 上级分销商.
     */
    @NotNull
    @ApiModelProperty(value = "审核ID")
    private Long id;

    /**
     * STATUS.
     */
    @NotNull
    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "审核备注")
    private String handleNote;
}
