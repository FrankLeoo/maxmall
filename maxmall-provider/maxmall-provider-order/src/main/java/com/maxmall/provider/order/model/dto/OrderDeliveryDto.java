package com.maxmall.provider.order.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "订单批量发货")
public class OrderDeliveryDto implements Serializable {

    @NotNull
    @ApiModelProperty("订单id")
    private Long orderId;

    @NotNull
    @ApiModelProperty("物流公司")
    private String deliveryCompany;

    @NotNull
    @ApiModelProperty("物流单号")
    private String deliverySn;
}
