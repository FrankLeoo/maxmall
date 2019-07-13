package com.maxmall.provider.merchant.model.vo;

import com.maxmall.common.base.dto.BaseVo;
import lombok.Data;

@Data
public class ShopVo extends BaseVo {
    /**
     * 商户Id.
     */
    private Long merchantId;

    /**
     * 地址纬度.
     */
    private Double latitude;

    /**
     * 地址经度.
     */
    private Double longitude;

    /**
     * 店铺主图片.
     */
    private String pic;

    /**
     * 店铺名称.
     */
    private String name;

    /**
     * 店铺标题.
     */
    private String title;

    /**
     * 店铺相册 逗号分割.
     */
    private String albumPics;

    /**
     * 商户编号.
     */
    private String merchantSn;

    /**
     * 店铺描述信息.
     */
    private String description;

    /**
     * 联系人电话.
     */
    private String contactPhone;

    /**
     * 城市.
     */
    private String receiverCity;

    /**
     * 区.
     */
    private String receiverRegion;

    /**
     * 联系人姓名.
     */
    private String contactUsername;

    /**
     * 省份/直辖市.
     */
    private String receiverProvince;

    /**
     * 详细地址.
     */
    private String receiverDetailAddress;

    /**
     * 状态.
     */
    private Integer status;
}
