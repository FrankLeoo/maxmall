package com.maxmall.provider.order.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(value = "退货单审核")
public class ApplyStatusDto implements Serializable {

    @NotNull
    @ApiModelProperty("服务单号")
    private Long id;

    @ApiModelProperty("处理备注")
    private String handleNote;

    /**
     * 收货方式 0：无需退货 1：客户回递
     */
    private Integer receiveType;

    /**
     * 收货人
     */
    private String receiveMan;
    /**
     * 收货电话
     */
    private String receivePhone;
    /**
     * 收货地址
     */
    private String receiveAddress;

    @NotNull
    @ApiModelProperty("申请状态：1->退货中；2->已完成；3->已拒绝")
    private Integer applyStatus;
}
