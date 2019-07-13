package com.maxmall.provider.merchant.model.domain.merchant;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 分销商表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "mcs_distributor")
public class DistributorDO extends BaseEntity {

    /**
     * 店铺ID.
     */
    @Column(name = "shop_id")
    private Long shopId;

    /**
     * 上级分销商.
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 商户Id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 管理员id.
     */
    @Column(name = "manager_user_id")
    private Long managerUserId;

    /**
     * 本级销售分销比率.
     */
    @Column(name = "self_sales_ratio")
    private Double selfSalesRatio;

    /**
     * 代理分销佣金.
     */
    @Column(name = "agent_commission")
    private Double agentCommission;

    /**
     * 祖父级销售分销比率.
     */
    @Column(name = "grand_sales_ratio")
    private Double grandSalesRatio;

    /**
     * 总佣金.
     */
    @Column(name = "total_commission")
    private Double totalCommission;

    /**
     * 父级销售分销比率.
     */
    @Column(name = "parent_sales_ratio")
    private Double parentSalesRatio;

    /**
     * 利润返点 按月结算
     */
    @Column(name = "profit_commission")
    private Double profitCommission;

    /**
     * 可提取佣金.
     */
    @Column(name = "extracted_commission")
    private Double extractedCommission;

    /**
     * 分销佣金.
     */
    @Column(name = "controbute_commission")
    private Double controbuteCommission;

    /**
     * 已提取佣金.
     */
    @Column(name = "unextracted_commissio")
    private Double unextractedCommissio;

    /**
     * 商户编号.
     */
    @Column(name = "merchant_sn")
    private String merchantSn;

    /**
     * 分销商编号（推广编号）.
     */
    @Column(name = "distributor_sn")
    private String distributorSn;

    /**
     * 管理员头像.
     */
    @Column(name = "manager_user_pic")
    private String managerUserPic;

    /**
     * 管理员名称.
     */
    @Column(name = "manager_user_name")
    private String managerUserName;

    /**
     * 分账账号.
     */
    @Column(name = "split_pay_account")
    private String splitPayAccount;

    /**
     * 分销等级.
     */
    @Column(name = "level_id")
    private Long levelId;
    /**
     * 分销等级.
     */
    @Column(name = "level")
    private Integer level;

    /**
     * 分销等级名称.
     */
    @Column(name = "level_name")
    private String levelName;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 会员数量.
     */
    @Column(name = "fans_num")
    private Integer fansNum;

    /**
     * 分账类型 0:个人 1:商户.
     */
    @Column(name = "split_pay_type")
    private Integer splitPayType;

    /**
     * 推广分销商数量.
     */
    @Column(name = "distributor_num")
    private Integer distributorNum;

}
