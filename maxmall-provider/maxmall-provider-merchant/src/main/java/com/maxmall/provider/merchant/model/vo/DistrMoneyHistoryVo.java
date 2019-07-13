package com.maxmall.provider.merchant.model.vo;

import com.maxmall.common.base.dto.BaseVo;
import lombok.Data;

@Data
public class DistrMoneyHistoryVo extends BaseVo {

    /**
     * 订单id.
     */
    private Long sourceId;

    /**
     * 商户Id.
     */
    private Long merchantId;

    /**
     * 分销商id.
     */
    private Long distributorId;

    /**
     * 操作员id.
     */
    private Long handlerUserId;

    /**
     * 操作金额.
     */
    private Double money;

    /**
     * 操作记录.
     */
    private String history;

    /**
     * 操作员头像.
     */
    private String handlerUserPic;

    /**
     * 操作员名称.
     */
    private String handlerUserName;

    /**
     * 操作类型 0:分销 1:返点 2:提现.
     */
    private Integer type;

    /**
     * 状态.
     */
    private Integer status;
}
