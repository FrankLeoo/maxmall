package com.maxmall.provider.order.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "退货原因状态更新参数")
public class ReturnReasonStatusDto implements Serializable {

    /**
     * 原因名称.
     */
    @NotNull
    @ApiModelProperty(value = "原因id")
    private Long id;

    @NotNull
    @ApiModelProperty(value = "状态")
    private Integer status;

}
