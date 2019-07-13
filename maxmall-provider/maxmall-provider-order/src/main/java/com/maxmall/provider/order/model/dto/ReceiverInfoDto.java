package com.maxmall.provider.order.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "订单收货人信息")
public class ReceiverInfoDto implements Serializable {

    @NotNull
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @NotNull
    @Length(max = 64)
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;

    @NotNull
    @Length(max = 32)
    @ApiModelProperty(value = "收货人电话")
    private String receiverPhone;

    @Length(max = 16)
    @ApiModelProperty(value = "收货邮政编码")
    private String receiverPostCode;

    @Length(max = 255)
    @ApiModelProperty(value = "收货详细地址")
    private String receiverDetailAddress;

    @Length(max = 64)
    @ApiModelProperty(value = "收货省份")
    private String receiverProvince;

    @Length(max = 64)
    @ApiModelProperty(value = "收货市区")
    private String receiverCity;

    @Length(max = 64)
    @ApiModelProperty(value = "收货乡镇")
    private String receiverRegion;

}
