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
@ApiModel(value = "分销商分页查询Dto ")
public class QueryDistrbutorAuditDto implements Serializable {

    /**
     * 上级分销商.
     */
    @ApiModelProperty(value = "上级分销商")
    private Long parentId;

    /**
     * 分销商编号（推广编号）.
     */
    @ApiModelProperty(value = "分销商编号")
    private String distributorSn;

    /**
     * STATUS.
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    private Integer pageNum;

    private Integer pageSize;

    @Override
    public String toString() {
        return "QueryDistrbutorDto{" +
                "parentId=" + parentId +
                ", distributorSn='" + distributorSn + '\'' +
                ", status=" + status +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
