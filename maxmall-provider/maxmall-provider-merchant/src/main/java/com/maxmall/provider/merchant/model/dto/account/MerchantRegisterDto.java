package com.maxmall.provider.merchant.model.dto.account;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 商户信息参数
 */
@Data
@ApiModel(value = "商户信息参数")
public class MerchantRegisterDto implements Serializable {

    /**
     * 商户名称
     */
    private String name;

    /**
     * 商户主图片.
     */
    private String pic;

    /**
     * 商户相册 逗号分割.
     */
    private String albumPics;

    /**
     * 商户描述信息.
     */
    private String description;

    /**
     * 联系人姓名.
     */
    private String contactUsername;

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
     * 省份/直辖市.
     */
    private String receiverProvince;

    /**
     * 详细地址.
     */
    private String receiverDetailAddress;
}
