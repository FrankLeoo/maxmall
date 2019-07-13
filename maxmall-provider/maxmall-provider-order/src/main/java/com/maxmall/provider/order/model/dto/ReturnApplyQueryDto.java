package com.maxmall.provider.order.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@ApiModel(value = "退货单查询参数")
public class ReturnApplyQueryDto implements Serializable {

    @ApiModelProperty("服务单号")
    private Long id;

    @ApiModelProperty(value = "收货人姓名/号码")
    private String receiverKeyword;

    @ApiModelProperty(value = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private Integer applyStatus;

    @ApiModelProperty(value = "处理人员")
    private String handleManName;

    private Integer pageNum;

    private Integer pageSize;

    @Override
    public String toString() {
        return "ReturnApplyQueryDto{" +
                "id=" + id +
                ", receiverKeyword='" + receiverKeyword + '\'' +
                ", applyStatus=" + applyStatus +
                ", handleManName='" + handleManName + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
