package com.maxmall.provider.order.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单查询参数
 * Created by macro on 2018/10/11.
 */
@Data
@ApiModel(value = "订单查询参数")
public class OrderQueryDto {

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "收货人姓名/号码")
    private String receiverKeyword;

    @ApiModelProperty(value = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单类型：0->正常订单；1->秒杀订单")
    private Integer orderType;

    @ApiModelProperty(value = "订单来源：0->PC订单；1->app订单")
    private Integer sourceType;

    @ApiModelProperty(value = "订单开始时间")
    private String createTimeStart;

    @ApiModelProperty(value = "订单结束时间")
    private String createTimeEnd;

    @ApiModelProperty(value = "活动ID")
    private Long promotionId;

    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    private Integer pageNum;

    private Integer pageSize;
}
