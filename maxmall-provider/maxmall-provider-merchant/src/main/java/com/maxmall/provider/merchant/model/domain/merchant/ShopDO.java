package com.maxmall.provider.merchant.model.domain.merchant;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 店铺表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "mcs_shop")
public class ShopDO extends BaseEntity {

    /**
     * 商户Id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 地址纬度.
     */
    @Column(name = "latitude")
    private Double latitude;

    /**
     * 地址经度.
     */
    @Column(name = "longitude")
    private Double longitude;

    /**
     * 店铺主图片.
     */
    @Column(name = "pic")
    private String pic;

    /**
     * 店铺名称.
     */
    @Column(name = "name")
    private String name;

    /**
     * 店铺标题.
     */
    @Column(name = "title")
    private String title;

    /**
     * 店铺相册 逗号分割.
     */
    @Column(name = "album_pics")
    private String albumPics;

    /**
     * 商户编号.
     */
    @Column(name = "merchant_sn")
    private String merchantSn;

    /**
     * 店铺描述信息.
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
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

}
