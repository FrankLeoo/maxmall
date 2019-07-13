package com.maxmall.provider.order.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "退货单确认收货")
public class ReceiveConfirmDto implements Serializable {

    @NotNull
    @ApiModelProperty("服务单号")
    private Long id;

    /**
     * 收货备注
     */
    private String receivedNote;

}
