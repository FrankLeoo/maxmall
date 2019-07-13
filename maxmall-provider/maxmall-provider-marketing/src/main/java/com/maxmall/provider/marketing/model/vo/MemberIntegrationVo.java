package com.maxmall.provider.marketing.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MemberIntegrationVo implements Serializable {

    private Long id;
    /**
     * 会员ID.
     */
    private Long memberId;

    /**
     * 商户Id.
     */
    private Long merchantId;

    /**
     * 操作人ID.
     */
    private Long operateManId;

    /**
     * 分销商ID.
     */
    private Long distributorId;

    /**
     * 会员微信头像.
     */
    private String memberIcon;

    /**
     * 操作备注.
     */
    private String operateNote;

    /**
     * 会员微信昵称.
     */
    private String memberNickname;

    /**
     * 状态.
     */
    private Integer status;

    /**
     * 改变类型：0->增加；1->减少.
     */
    private Integer changeType;

    /**
     * 积分来源：0->购物；1->管理员修改.
     */
    private Integer sourceType;

    /**
     * 积分改变数量.
     */
    private Integer changeCount;

    /**
     * 操作人.
     */
    private String operateManIcon;

    /**
     * 操作人名称.
     */
    private String operateManName;

    private Date createTime;
}
