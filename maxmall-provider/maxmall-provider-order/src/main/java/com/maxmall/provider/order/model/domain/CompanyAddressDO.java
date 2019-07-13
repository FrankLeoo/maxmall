package com.maxmall.provider.order.model.domain;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 公司收发货地址表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "oms_company_address")
public class CompanyAddressDO extends BaseEntity {

    /**
     * 商户id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 市.
     */
    @Column(name = "city")
    private String city;

    /**
     * 收发货人姓名.
     */
    @Column(name = "name")
    private String name;

    /**
     * 收货人电话.
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 区.
     */
    @Column(name = "region")
    private String region;

    /**
     * 省/直辖市.
     */
    @Column(name = "province")
    private String province;

    /**
     * 地址名称.
     */
    @Column(name = "address_name")
    private String addressName;

    /**
     * 详细地址.
     */
    @Column(name = "detail_address")
    private String detailAddress;

    /**
     * 类型 1:收货 2:发货.
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 是否默认地址.
     */
    @Column(name = "is_default")
    private Integer isDefault;

    /**
     * CREATE_TIME.
     */
    @Column(name = "create_time")
    private Date createTime;

}
