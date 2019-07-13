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
@Table(name = "mcs_distributor_audit")
public class DistributorAuditDO extends BaseEntity {

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
     * 祖父级销售分销比率.
     */
    @Column(name = "grand_sales_ratio")
    private Double grandSalesRatio;

    /**
     * 父级销售分销比率.
     */
    @Column(name = "parent_sales_ratio")
    private Double parentSalesRatio;

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
     * 是否需要会费 0:否 1:是.
     */
    @Column(name = "is_dues")
    private Integer isDues;

    /**
     * 需要会费.
     */
    @Column(name = "dues_point")
    private Double duesPoint;

    /**
     * 是否支付.
     */
    @Column(name = "is_payed")
    private Integer isPayed;

    /**
     * 内部支付单号.
     */
    @Column(name = "inner_pay_sn")
    private String innerPaySn;

    /**
     * 外部支付单号.
     */
    @Column(name = "outer_pay_sn")
    private String outerPaySn;

    /**
     * 支付时间.
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 审核原因.
     */
    @Column(name = "reason")
    private String reason;

    /**
     * 处理备注.
     */
    @Column(name = "handle_note")
    private String handleNote;

    /**
     * 处理时间.
     */
    @Column(name = "handle_time")
    private Date handleTime;

    /**
     * 处理人员id.
     */
    @Column(name = "handle_man_id")
    private Long handleManId;

    /**
     * 处理人员名称.
     */
    @Column(name = "handle_man_name")
    private String handleManName;

    /**
     * 分账类型 0:个人 1:商户.
     */
    @Column(name = "split_pay_type")
    private Integer splitPayType;

}
