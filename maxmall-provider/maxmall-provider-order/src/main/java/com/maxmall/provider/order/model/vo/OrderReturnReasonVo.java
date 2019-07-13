package com.maxmall.provider.order.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderReturnReasonVo implements Serializable {

    private Long id;
    /**
     * 商户id.
     */
    private Long merchantId;

    /**
     * 退货类型.
     */
    private String name;

    /**
     * SORT.
     */
    private Integer sort;

    /**
     * 状态：0->不启用；1->启用.
     */
    private Integer status;

    /**
     * 添加时间.
     */
    private Date createTime;
}
