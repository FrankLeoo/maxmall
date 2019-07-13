package com.maxmall.provider.merchant.model.vo;

import com.maxmall.common.base.dto.BaseVo;
import lombok.Data;

@Data
public class DistributorVo extends BaseVo {

    /**
     * 店铺ID.
     */
    private Long shopId;

    /**
     * 上级分销商.
     */
    private Long parentId;

    /**
     * 商户Id.
     */
    private Long merchantId;

    /**
     * 管理员id.
     */
    private Long managerUserId;

    /**
     * 本级销售分销比率.
     */
    private Double selfSalesRatio;

    /**
     * 代理分销佣金.
     */
    private Double agentCommission;

    /**
     * 利润返点 按月结算
     */
    private Double profitCommission;

    /**
     * 祖父级销售分销比率.
     */
    private Double grandSalesRatio;

    /**
     * 总佣金.
     */
    private Double totalCommission;

    /**
     * 父级销售分销比率.
     */
    private Double parentSalesRatio;

    /**
     * 可提取佣金.
     */
    private Double extractedCommission;

    /**
     * 分销佣金.
     */
    private Double controbuteCommission;

    /**
     * 已提取佣金.
     */
    private Double unextractedCommissio;

    /**
     * 商户编号.
     */
    private String merchantSn;

    /**
     * 分销商编号（推广编号）.
     */
    private String distributorSn;

    /**
     * 管理员头像.
     */
    private String managerUserPic;

    /**
     * 管理员名称.
     */
    private String managerUserName;

    /**
     * 分销等级.
     */
    private Integer level;

    /**
     * 分销等级.
     */
    private Long levelId;

    /**
     * 分销等级名称.
     */
    private String levelName;

    /**
     * 状态.
     */
    private Integer status;

    /**
     * 会员数量.
     */
    private Integer fansNum;

    /**
     * 分账类型 0:个人 1:商户.
     */
    private Integer splitPayType;

    /**
     * 分账账号.
     */
    private String splitPayAccount;

    /**
     * 推广分销商数量.
     */
    private Integer distributorNum;

    /**
     * 分销商店铺
     */
    private ShopVo shop;

    /**
     * 上级分销商
     */
    private DistributorVo parent;

}
