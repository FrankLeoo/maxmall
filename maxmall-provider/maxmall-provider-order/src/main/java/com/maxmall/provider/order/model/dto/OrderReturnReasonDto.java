package com.maxmall.provider.order.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 创建退款原因param
 */
@Data
@ApiModel(value = "订单退货原因")
public class OrderReturnReasonDto implements Serializable {

    /**
     * 退货类型.
     */
    @ApiModelProperty(value = "退货原因名称")
    @Length(max = 64)
    @NotNull
    private String name;

    /**
     * SORT.
     */
    @ApiModelProperty(value = "退货原因排序")
    private Integer sort;

}
