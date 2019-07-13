package com.maxmall.provider.order.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@ApiModel(value = "退货原因查询参数")
public class ReturnReasonQueryDto implements Serializable {

    /**
     * 原因名称.
     */
    @Length(max = 32)
    @ApiModelProperty(value = "原因名称")
    private String name;

    private Integer pageNum;

    private Integer pageSize;

    @Override
    public String toString() {
        return "ReturnReasonQueryDto{" +
                "name='" + name + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
