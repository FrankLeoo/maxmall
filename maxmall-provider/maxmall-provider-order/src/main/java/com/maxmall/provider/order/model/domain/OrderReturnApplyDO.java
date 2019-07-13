package com.maxmall.provider.order.model.domain;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
import java.math.BigDecimal;

/**
 * The table 订单退货申请
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "oms_order_return_apply")
public class OrderReturnApplyDO extends BaseEntity {

    /**
     * 订单id.
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 会员.
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * 退货商品id.
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 供应商.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 处理人员id.
     */
    @Column(name = "handle_man_id")
    private Long handleManId;

    /**
     * 订单itemid.
     */
    @Column(name = "order_item_id")
    private Long orderItemId;

    /**
     * 分销商id.
     */
    @Column(name = "distributor_id")
    private Long distributorId;

    /**
     * 确认收货人员id.
     */
    @Column(name = "received_man_id")
    private Long receivedManId;

    /**
     * 商品品牌id.
     */
    @Column(name = "product_brand_id")
    private Long productBrandId;

    /**
     * 备注.
     */
    @Column(name = "note")
    private String note;

    /**
     * 原因.
     */
    @Column(name = "reason")
    private String reason;

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
     * 凭证图片，以逗号隔开.
     */
    @Column(name = "proof_pics")
    private String proofPics;

    /**
     * 处理备注.
     */
    @Column(name = "handle_note")
    private String handleNote;

    /**
     * 会员头像.
     */
    @Column(name = "member_icon")
    private String memberIcon;

    /**
     * 会员用户名.
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 商品图片.
     */
    @Column(name = "product_pic")
    private String productPic;

    /**
     * 收货人.
     */
    @Column(name = "receive_man")
    private String receiveMan;

    /**
     * 退货人姓名.
     */
    @Column(name = "return_name")
    private String returnName;

    /**
     * 回递时间.
     */
    @Column(name = "express_time")
    private String expressTime;

    /**
     * 商品名称.
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 退货人电话.
     */
    @Column(name = "return_phone")
    private String returnPhone;

    /**
     * 收货人电话.
     */
    @Column(name = "receive_phone")
    private String receivePhone;

    /**
     * 收货备注.
     */
    @Column(name = "received_note")
    private String receivedNote;

    /**
     * 处理人员.
     */
    @Column(name = "handle_man_name")
    private String handleManName;

    /**
     * 商品销售属性：颜色：红色；尺码：xl;.
     */
    @Column(name = "sale_attribute")
    private String saleAttribute;

    /**
     * 物流公司(配送方式).
     */
    @Column(name = "express_company")
    private String expressCompany;

    /**
     * 收货地址.
     */
    @Column(name = "receive_address")
    private String receiveAddress;

    /**
     * 分销商名称.
     */
    @Column(name = "distributor_name")
    private String distributorName;

    /**
     * 确认收货人员名称.
     */
    @Column(name = "received_man_name")
    private String receivedManName;

    /**
     * 商品品牌.
     */
    @Column(name = "product_brand_name")
    private String productBrandName;

    /**
     * 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝.
     */
    @Column(name = "apply_status")
    private Integer applyStatus;

    /**
     * 状态启用状态：0->不启用；1->启用
     */
    @Column(name="status")
    private Integer status;

    /**
     * 是否收货.
     */
    @Column(name = "is_received")
    private Integer isReceived;

    /**
     * 收货方式：0->无需收货（直接退货）；1->客户回递（等待确认收货）.
     */
    @Column(name = "receive_type")
    private Integer receiveType;

    /**
     * 退货数量.
     */
    @Column(name = "return_count")
    private Integer returnCount;

    /**
     * 处理时间.
     */
    @Column(name = "handle_time")
    private Date handleTime;

    /**
     * 收货时间.
     */
    @Column(name = "received_time")
    private Date receivedTime;

    /**
     * 商品单价.
     */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /**
     * 退款金额.
     */
    @Column(name = "return_amount")
    private BigDecimal returnAmount;

    /**
     * 商品实际支付单价.
     */
    @Column(name = "product_real_price")
    private BigDecimal productRealPrice;

    /**
     * 销售数量
     */
    @Column(name = "product_quantity")
    private Integer productQuantity;

    /**
     * 销售金额
     */
    @Column(name = "real_amount")
    private BigDecimal realAmount;

}
