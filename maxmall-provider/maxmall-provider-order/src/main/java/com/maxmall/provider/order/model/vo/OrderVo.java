package com.maxmall.provider.order.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderVo implements Serializable {

    private Long id;
    /**
     * 优惠券id.
     */
    private Long couponId;

    /**
     * 会员id.
     */
    private Long memberId;

    /**
     * 商户id.
     */
    private Long mechantId;

    /**
     * 活动id.
     */
    private Long promotionId;

    /**
     * 分销商id.
     */
    private Long distributorId;

    /**
     * 门店id.
     */
    private Long shopId;

    /**
     * 门店名称.
     */
    private String shopName;

    /**
     * 订单备注.
     */
    private String note;

    /**
     * 订单编号.
     */
    private String orderSn;

    /**
     * 物流单号.
     */
    private String expressSn;

    /**
     * 发票抬头.
     */
    private String billHeader;

    /**
     * 优惠券名称.
     */
    private String couponName;

    /**
     * 会员头像.
     */
    private String memberIcon;

    /**
     * 用户帐号.
     */
    private String memberName;

    /**
     * 发票内容.
     */
    private String billContent;

    /**
     * 城市.
     */
    private String receiverCity;

    /**
     * 收货人姓名.
     */
    private String receiverName;

    /**
     * 支付信息.
     */
    private String phasePayments;

    /**
     * 活动名称.
     */
    private String promotionName;

    /**
     * 收货人电话.
     */
    private String receiverPhone;

    /**
     * 分销商头像.
     */
    private String distributorPic;

    /**
     * 物流公司(配送方式).
     */
    private String expressCompany;

    /**
     * 区.
     */
    private String receiverRegion;

    /**
     * 分销商名称.
     */
    private String distributorName;

    /**
     * 收货人邮编.
     */
    private String receiverPostCode;

    /**
     * 省份/直辖市.
     */
    private String receiverProvince;

    /**
     * 收票人邮箱.
     */
    private String billReceiverEmail;

    /**
     * 收票人电话.
     */
    private String billReceiverPhone;

    /**
     * 内部支付流水号.
     */
    private String innerTransactionNo;

    /**
     * 外部支付流水号.
     */
    private String outerTransactionNo;

    /**
     * 详细地址.
     */
    private String receiverDetailAddress;

    /**
     * 可以活动的成长值.
     */
    private Integer growth;

    private Integer status;

    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单.
     */
    private Integer orderStatus;

    /**
     * 是否支付.
     */
    private Integer isPayed;

    /**
     * 支付方式：0->未支付；1->支付宝；2->微信.
     */
    private Integer payType;

    /**
     * 发票类型：0->不开发票；1->电子发票；2->纸质发票.
     */
    private Integer billType;

    /**
     * 是否结算.
     */
    private Integer isSettle;

    /**
     * 是否评价.
     */
    private Integer isComment;

    /**
     * 是否收货状态：0->未确认；1->已确认.
     */
    private Integer isReceive;

    /**
     * 订单类型：0->正常订单；1->秒杀订单.
     */
    private Integer orderType;

    /**
     * 优惠券类型.
     */
    private Integer couponType;

    /**
     * 订单来源：0->PC订单；1->app订单.
     */
    private Integer sourceType;

    /**
     * 物流类型 0:手动发货; 1:系统自动发货.
     */
    private Integer expressType;

    /**
     * 可以获得的积分.
     */
    private Integer integration;

    /**
     * 活动类型.
     */
    private Integer promotionType;

    /**
     * 自动确认时间（天）.
     */
    private Integer autoConfirmDay;

    /**
     * 是否分销订单.
     */
    private Integer isFenxiaoOrder;

    /**
     * 下单时使用的积分.
     */
    private Integer useIntegration;

    /**
     * 提交时间.
     */
    private Date createTime;

    /**
     * 结算时间.
     */
    private Date settleTime;

    /**
     * 评价时间.
     */
    private Date commentTime;

    /**
     * 发货时间.
     */
    private Date expressTime;

    /**
     * 支付时间.
     */
    private Date paymentTime;

    /**
     * 确认收货时间.
     */
    private Date receiveTime;

    /**
     * 应付金额（实际支付金额）.
     */
    private BigDecimal payAmount;

    /**
     * 订单总金额.
     */
    private BigDecimal totalAmount;

    /**
     * 优惠券抵扣金额.
     */
    private BigDecimal couponAmount;

    /**
     * 运费金额.
     */
    private BigDecimal freightAmount;

    /**
     * 本级销售分销比率.
     */
    private BigDecimal selfSalesRatio;

    /**
     * 祖父级销售分销比率.
     */
    private BigDecimal grandSalesRatio;

    /**
     * 促销优化金额（促销价、满减、阶梯价）.
     */
    private BigDecimal promotionAmount;

    /**
     * 父级销售分销比率.
     */
    private BigDecimal parentSalesRatio;

    /**
     * 积分抵扣金额.
     */
    private BigDecimal integrationAmount;

    /**
     * 订单商品信息
     */
    private List<OrderItemVo> productItems;
}
