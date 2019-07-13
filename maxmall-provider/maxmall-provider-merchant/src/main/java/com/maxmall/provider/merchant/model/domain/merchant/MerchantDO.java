package com.maxmall.provider.merchant.model.domain.merchant;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 商户主表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "mcs_merchant")
public class MerchantDO extends BaseEntity {

    /**
     * 管理员id.
     */
    @Column(name = "manager_user_id")
    private Long managerUserId;

    /**
     * 商户主图片.
     */
    @Column(name = "pic")
    private String pic;

    /**
     * 商户名称\r\n商户名称.
     */
    @Column(name = "name")
    private String name;

    /**
     * 商户相册 逗号分割.
     */
    @Column(name = "album_pics")
    private String albumPics;

    /**
     * 商户编号.
     */
    @Column(name = "merchant_sn")
    private String merchantSn;

    /**
     * 商户描述信息.
     */
    @Column(name = "description")
    private String description;

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

    /**
     * 商户状态.
     */
    @Column(name = "status")
    private Integer status;

}
