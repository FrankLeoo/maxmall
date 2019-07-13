package com.maxmall.provider.merchant.model.vo;

import com.maxmall.common.base.dto.BaseVo;
import lombok.Data;

import java.util.Date;

@Data
public class DistributorAuditVo extends BaseVo {

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
     * 祖父级销售分销比率.
     */
    private Double grandSalesRatio;

    /**
     * 父级销售分销比率.
     */
    private Double parentSalesRatio;

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
    private Long levelId;
    /**
     * 分销等级.
     */
    private Integer level;

    /**
     * 分销等级名称.
     */
    private String levelName;

    /**
     * 是否需要会费 0:否 1:是.
     */
    private Integer isDues;

    //会费
    private Double duesPoint;

    /**
     * 需要会费.
     */
    private Integer isPayed;

    /**
     * 需要会费.
     */
    private String innerPaySn;

    /**
     * 需要会费.
     */
    private String outerPaySn;

    /**
     * 需要会费.
     */
    private Date payTime;

    /**
     * 状态.
     */
    private Integer status;

    /**
     * 分账类型 0:个人 1:商户.
     */
    private Integer splitPayType;

    /**
     * 分账账号.
     */
    private String splitPayAccount;

    /**
     * 审核原因.
     */
    private String reason;

    /**
     * 处理备注.
     */
    private String handleNote;

    /**
     * 处理时间.
     */
    private Date handleTime;

    /**
     * 处理人员id.
     */
    private Long handleManId;

    /**
     * 处理人员名称.
     */
    private String handleManName;

    /**
     * 分销商店铺
     */
    private ShopVo shop;
}
