package com.maxmall.provider.order.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderReturnApplyVo implements Serializable {

    private Long id;
    /**
     * 订单id.
     */
    private Long orderId;

    /**
     * 会员.
     */
    private Long memberId;

    /**
     * 退货商品id.
     */
    private Long productId;

    /**
     * 供应商.
     */
    private Long merchantId;

    /**
     * 处理人员id.
     */
    private Long handleManId;

    /**
     * 订单itemid.
     */
    private Long orderItemId;

    /**
     * 分销商id.
     */
    private Long distributorId;

    /**
     * 确认收货人员id.
     */
    private Long receivedManId;

    /**
     * 商品品牌id.
     */
    private Long productBrandId;

    /**
     * 备注.
     */
    private String note;

    /**
     * 原因.
     */
    private String reason;

    /**
     * 订单编号.
     */
    private String orderSn;

    /**
     * 物流单号.
     */
    private String expressSn;

    /**
     * 凭证图片，以逗号隔开.
     */
    private String proofPics;

    /**
     * 处理备注.
     */
    private String handleNote;

    /**
     * 会员头像.
     */
    private String memberIcon;

    /**
     * 会员用户名.
     */
    private String memberName;

    /**
     * 商品图片.
     */
    private String productPic;

    /**
     * 收货人.
     */
    private String receiveMan;

    /**
     * 退货人姓名.
     */
    private String returnName;

    /**
     * 回递时间.
     */
    private String expressTime;

    /**
     * 商品名称.
     */
    private String productName;

    /**
     * 退货人电话.
     */
    private String returnPhone;

    /**
     * 收货人电话.
     */
    private String receivePhone;

    /**
     * 收货备注.
     */
    private String receivedNote;

    /**
     * 处理人员.
     */
    private String handleManName;

    /**
     * 商品销售属性：颜色：红色；尺码：xl;.
     */
    private String saleAttribute;

    /**
     * 物流公司(配送方式).
     */
    private String expressCompany;

    /**
     * 收货地址.
     */
    private String receiveAddress;

    /**
     * 分销商名称.
     */
    private String distributorName;

    /**
     * 确认收货人员名称.
     */
    private String receivedManName;

    /**
     * 商品品牌.
     */
    private String productBrandName;

    /**
     * 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝.
     */
    private Integer applyStatus;

    private Integer status;

    /**
     * 是否收货.
     */
    private Integer isReceived;

    /**
     * 收货方式：0->无需收货（直接退货）；1->客户回递（等待确认收货）.
     */
    private Integer receiveType;

    /**
     * 处理时间.
     */
    private Date handleTime;

    /**
     * 收货时间.
     */
    private Date receivedTime;

    /**
     * 商品单价.
     */
    private BigDecimal productPrice;

    /**
     * 退款金额.
     */
    private BigDecimal returnAmount;

    /**
     * 商品实际支付单价.
     */
    private BigDecimal productRealPrice;

    /**
     * 提交时间.
     */
    private Date createTime;

    /**
     * 退货数量.
     */
    private Integer returnCount;

    /**
     * 销售数量
     */
    private Integer productQuantity;

    /**
     * 销售金额
     */
    private BigDecimal realAmount;
}
