package com.maxmall.provider.order.model.domain;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
import java.math.BigDecimal;

/**
 * The table 订单表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "oms_order")
public class OrderDO extends BaseEntity {

    /**
     * 优惠券id.
     */
    @Column(name = "coupon_id")
    private Long couponId;

    /**
     * 会员id.
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * 商户id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 活动id.
     */
    @Column(name = "promotion_id")
    private Long promotionId;

    /**
     * 分销商id.
     */
    @Column(name = "distributor_id")
    private Long distributorId;

    /**
     * 门店id.
     */
    @Column(name = "shop_id")
    private Long shopId;

    /**
     * 门店名称.
     */
    @Column(name = "shop_name")
    private String shopName;

    /**
     * 订单备注.
     */
    @Column(name = "note")
    private String note;

    /**
     * 订单编号.
     */
    @Column(name = "order_sn")
    private String orderSn;

    /**
     * 物流单号.
     */
    @Column(name = "express_sn")
    private String expressSn;

    /**
     * 发票抬头.
     */
    @Column(name = "bill_header")
    private String billHeader;

    /**
     * 优惠券名称.
     */
    @Column(name = "coupon_name")
    private String couponName;

    /**
     * 会员头像.
     */
    @Column(name = "member_icon")
    private String memberIcon;

    /**
     * 用户帐号.
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 发票内容.
     */
    @Column(name = "bill_content")
    private String billContent;

    /**
     * 城市.
     */
    @Column(name = "receiver_city")
    private String receiverCity;

    /**
     * 收货人姓名.
     */
    @Column(name = "receiver_name")
    private String receiverName;

    /**
     * 支付信息.
     */
    @Column(name = "phase_payments")
    private String phasePayments;

    /**
     * 活动名称.
     */
    @Column(name = "promotion_name")
    private String promotionName;

    /**
     * 收货人电话.
     */
    @Column(name = "receiver_phone")
    private String receiverPhone;

    /**
     * 分销商头像.
     */
    @Column(name = "distributor_pic")
    private String distributorPic;

    /**
     * 物流公司(配送方式).
     */
    @Column(name = "express_company")
    private String expressCompany;

    /**
     * 区.
     */
    @Column(name = "receiver_region")
    private String receiverRegion;

    /**
     * 分销商名称.
     */
    @Column(name = "distributor_name")
    private String distributorName;

    /**
     * 收货人邮编.
     */
    @Column(name = "receiver_post_code")
    private String receiverPostCode;

    /**
     * 省份/直辖市.
     */
    @Column(name = "receiver_province")
    private String receiverProvince;

    /**
     * 收票人邮箱.
     */
    @Column(name = "bill_receiver_email")
    private String billReceiverEmail;

    /**
     * 收票人电话.
     */
    @Column(name = "bill_receiver_phone")
    private String billReceiverPhone;

    /**
     * 内部支付流水号.
     */
    @Column(name = "inner_transaction_no")
    private String innerTransactionNo;

    /**
     * 外部支付流水号.
     */
    @Column(name = "outer_transaction_no")
    private String outerTransactionNo;

    /**
     * 详细地址.
     */
    @Column(name = "receiver_detail_address")
    private String receiverDetailAddress;

    /**
     * 可以活动的成长值.
     */
    @Column(name = "growth")
    private Integer growth;

    /**
     * 状态 1：启用 0：未启用
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单.
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 是否支付.
     */
    @Column(name = "is_payed")
    private Integer isPayed;

    /**
     * 支付方式：0->未支付；1->支付宝；2->微信.
     */
    @Column(name = "pay_type")
    private Integer payType;

    /**
     * 发票类型：0->不开发票；1->电子发票；2->纸质发票.
     */
    @Column(name = "bill_type")
    private Integer billType;

    /**
     * 是否结算.
     */
    @Column(name = "is_settle")
    private Integer isSettle;

    /**
     * 是否评价.
     */
    @Column(name = "is_comment")
    private Integer isComment;

    /**
     * 是否收货状态：0->未确认；1->已确认.
     */
    @Column(name = "is_receive")
    private Integer isReceive;

    /**
     * 订单类型：0->正常订单；1->秒杀订单.
     */
    @Column(name = "order_type")
    private Integer orderType;

    /**
     * 优惠券类型.
     */
    @Column(name = "coupon_type")
    private Integer couponType;

    /**
     * 订单来源：0->PC订单；1->app订单.
     */
    @Column(name = "source_type")
    private Integer sourceType;

    /**
     * 物流类型 0:手动发货; 1:系统自动发货.
     */
    @Column(name = "express_type")
    private Integer expressType;

    /**
     * 可以获得的积分.
     */
    @Column(name = "integration")
    private Integer integration;

    /**
     * 活动类型.
     */
    @Column(name = "promotion_type")
    private Integer promotionType;

    /**
     * 自动确认时间（天）.
     */
    @Column(name = "auto_confirm_day")
    private Integer autoConfirmDay;

    /**
     * 是否分销订单.
     */
    @Column(name = "is_fenxiao_order")
    private Integer isFenxiaoOrder;

    /**
     * 下单时使用的积分.
     */
    @Column(name = "use_integration")
    private Integer useIntegration;

    /**
     * 提交时间.
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 结算时间.
     */
    @Column(name = "settle_time")
    private Date settleTime;

    /**
     * 评价时间.
     */
    @Column(name = "comment_time")
    private Date commentTime;

    /**
     * 发货时间.
     */
    @Column(name = "express_time")
    private Date expressTime;

    /**
     * 支付时间.
     */
    @Column(name = "payment_time")
    private Date paymentTime;

    /**
     * 确认收货时间.
     */
    @Column(name = "receive_time")
    private Date receiveTime;

    /**
     * 应付金额（实际支付金额）.
     */
    @Column(name = "pay_amount")
    private BigDecimal payAmount;

    /**
     * 订单总金额.
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 优惠券抵扣金额.
     */
    @Column(name = "coupon_amount")
    private BigDecimal couponAmount;

    /**
     * 运费金额.
     */
    @Column(name = "freight_amount")
    private BigDecimal freightAmount;

    /**
     * 本级销售分销比率.
     */
    @Column(name = "self_sales_ratio")
    private BigDecimal selfSalesRatio;

    /**
     * 祖父级销售分销比率.
     */
    @Column(name = "grand_sales_ratio")
    private BigDecimal grandSalesRatio;

    /**
     * 促销优化金额（促销价、满减、阶梯价）.
     */
    @Column(name = "promotion_amount")
    private BigDecimal promotionAmount;

    /**
     * 父级销售分销比率.
     */
    @Column(name = "parent_sales_ratio")
    private BigDecimal parentSalesRatio;

    /**
     * 积分抵扣金额.
     */
    @Column(name = "integration_amount")
    private BigDecimal integrationAmount;

}
