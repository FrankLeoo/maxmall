package com.maxmall.provider.merchant.model.domain.merchant;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table MCS_MERCHANT_AUDIT
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "mcs_merchant_audit")
public class MerchantAuditDO extends BaseEntity {

    /**
     * 商户主图片.
     */
    @Column(name = "pic")
    private String pic;

    /**
     * 商户名称.
     */
    @Column(name = "name")
    private String name;

    /**
     * 审核原因.
     */
    @Column(name = "reason")
    private String reason;

    /**
     * 审核状态.
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 商户相册 逗号分割.
     */
    @Column(name = "album_pics")
    private String albumPics;

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
     * 商户描述信息.
     */
    @Column(name = "description")
    private String description;

    /**
     * 处理人员id.
     */
    @Column(name = "handle_man_id")
    private Long handleManId;

    /**
     * 联系人电话.
     */
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 城市.
     */
    @Column(name = "receiver_city")
    private String receiverCity;

    /**
     * 处理人员名称.
     */
    @Column(name = "handle_man_name")
    private String handleManName;

    /**
     * 管理员id.
     */
    @Column(name = "manager_user_id")
    private Long managerUserId;

    /**
     * 管理员头像.
     */
    @Column(name = "manager_user_pic")
    private String managerUserPic;

    /**
     * 区.
     */
    @Column(name = "receiver_region")
    private String receiverRegion;

    /**
     * 联系人姓名.
     */
    @Column(name = "contact_username")
    private String contactUsername;

    /**
     * 管理员名称.
     */
    @Column(name = "manager_user_name")
    private String managerUserName;

    /**
     * 省份/直辖市.
     */
    @Column(name = "receiver_province")
    private String receiverProvince;

    /**
     * 详细地址.
     */
    @Column(name = "receiver_detail_address")
    private String receiverDetailAddress;

}
