package com.maxmall.provider.merchant.model.dto.distributor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * The class Modify user status dto.
 *
 * @author maxmall.net@gmail.com
 */
@Data
@ApiModel(value = "分销商操作记录分页查询Dto ")
public class QueryDistrHistoryDto implements Serializable {

    /**
     * 上级分销商.
     */
    @ApiModelProperty(value = "分销商id")
    private Long distributorId;

    @ApiModelProperty(value = "操作类型 0:分销 1:返点 2:提现")
    private Integer type;

    private Integer pageNum;

    private Integer pageSize;

    @Override
    public String toString() {
        return "QueryDistrHistoryDto{" +
                "distributorId=" + distributorId +
                ", type=" + type +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
