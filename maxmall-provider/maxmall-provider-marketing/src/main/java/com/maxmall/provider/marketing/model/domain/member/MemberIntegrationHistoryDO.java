package com.maxmall.provider.marketing.model.domain.member;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 会员积分记录表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "msc_member_integration_history")
public class MemberIntegrationHistoryDO extends BaseEntity {

    /**
     * 会员ID.
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * 商户Id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 操作人ID.
     */
    @Column(name = "operate_man_id")
    private Long operateManId;

    /**
     * 分销商ID.
     */
    @Column(name = "distributor_id")
    private Long distributorId;

    /**
     * 会员微信头像.
     */
    @Column(name = "member_icon")
    private String memberIcon;

    /**
     * 操作备注.
     */
    @Column(name = "operate_note")
    private String operateNote;

    /**
     * 会员微信昵称.
     */
    @Column(name = "member_nickname")
    private String memberNickname;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 改变类型：0->增加；1->减少.
     */
    @Column(name = "change_type")
    private Integer changeType;

    /**
     * 积分来源：0->购物；1->管理员修改.
     */
    @Column(name = "source_type")
    private Integer sourceType;

    /**
     * 积分改变数量.
     */
    @Column(name = "change_count")
    private Integer changeCount;

    /**
     * 操作人.
     */
    @Column(name = "operate_man_icon")
    private String operateManIcon;

    /**
     * 操作人名称.
     */
    @Column(name = "operate_man_name")
    private String operateManName;

}
