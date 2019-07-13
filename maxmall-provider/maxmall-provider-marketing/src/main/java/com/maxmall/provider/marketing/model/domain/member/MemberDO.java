package com.maxmall.provider.marketing.model.domain.member;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 会员信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "msc_member")
public class MemberDO extends BaseEntity {

    /**
     * 邀请人id.
     */
    @Column(name = "inviter_id")
    private Long inviterId;

    /**
     * 商户Id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 分销商ID.
     */
    @Column(name = "distributor_id")
    private Long distributorId;

    /**
     * 等级ID.
     */
    @Column(name = "member_level_id")
    private Long memberLevelId;

    /**
     * 职业.
     */
    @Column(name = "job")
    private String job;

    /**
     * 城市.
     */
    @Column(name = "city")
    private String city;

    /**
     * 微信头像.
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 微信openid.
     */
    @Column(name = "openid")
    private String openid;

    /**
     * 生日.
     */
    @Column(name = "birthday")
    private String birthday;

    /**
     * 微信昵称.
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 邀请人头像.
     */
    @Column(name = "inviter_icon")
    private String inviterIcon;

    /**
     * 邀请人姓名.
     */
    @Column(name = "inviter_name")
    private String inviterName;

    /**
     * 等级名称.
     */
    @Column(name = "member_level_name")
    private String memberLevelName;

    /**
     * 性别 0->未知 1->男 2->女.
     */
    @Column(name = "gender")
    private Integer gender;

    /**
     * 成长值.
     */
    @Column(name = "growth")
    private Integer growth;

    /**
     * 状态 0->正常 1->禁用.
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 会员来源 0->微信.
     */
    @Column(name = "source_type")
    private Integer sourceType;

    /**
     * 积分.
     */
    @Column(name = "integration")
    private Integer integration;

}
