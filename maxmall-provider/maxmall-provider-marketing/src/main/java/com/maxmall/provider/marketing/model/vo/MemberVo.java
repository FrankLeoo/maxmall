package com.maxmall.provider.marketing.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberVo implements Serializable {

    private Long id;

    /**
     * 邀请人id.
     */
    private Long inviterId;

    /**
     * 商户Id.
     */
    private Long merchantId;

    /**
     * 分销商ID.
     */
    private Long distributorId;

    /**
     * 等级ID.
     */
    private Long memberLevelId;

    /**
     * 职业.
     */
    private String job;

    /**
     * 城市.
     */
    private String city;

    /**
     * 微信头像.
     */
    private String icon;

    /**
     * 微信openid.
     */
    private String openid;

    /**
     * 生日.
     */
    private String birthday;

    /**
     * 微信昵称.
     */
    private String nickname;

    /**
     * 邀请人头像.
     */
    private String inviterIcon;

    /**
     * 邀请人姓名.
     */
    private String inviterName;

    /**
     * 等级名称.
     */
    private String memberLevelName;

    /**
     * 性别 0->未知 1->男 2->女.
     */
    private Integer gender;

    /**
     * 成长值.
     */
    private Integer growth;

    /**
     * 状态 0->正常 1->禁用.
     */
    private Integer status;

    /**
     * 会员来源 0->微信.
     */
    private Integer sourceType;

    /**
     * 积分.
     */
    private Integer integration;

    /**
     * 会员账号统计
     */
    private MemberAccountVo account;

}
