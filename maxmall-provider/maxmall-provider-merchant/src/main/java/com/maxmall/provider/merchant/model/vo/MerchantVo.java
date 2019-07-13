package com.maxmall.provider.merchant.model.vo;

import com.maxmall.common.base.dto.BaseVo;
import lombok.Data;

@Data
public class MerchantVo extends BaseVo {

    /**
     * 管理员id.
     */
    private Long managerUserId;

    /**
     * 商户主图片.
     */
    private String pic;

    /**
     * 商户名称\r\n商户名称.
     */
    private String name;

    /**
     * 商户相册 逗号分割.
     */
    private String albumPics;

    /**
     * 商户编号.
     */
    private String merchantSn;

    /**
     * 商户描述信息.
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
     * 管理员头像.
     */
    private String managerUserPic;

    /**
     * 区.
     */
    private String receiverRegion;

    /**
     * 联系人姓名.
     */
    private String contactUsername;

    /**
     * 管理员名称.
     */
    private String managerUserName;

    /**
     * 省份/直辖市.
     */
    private String receiverProvince;

    /**
     * 详细地址.
     */
    private String receiverDetailAddress;

    /**
     * 商户状态.
     */
    private Integer status;

    private MerchantConfigVo merchantConfig;
}
