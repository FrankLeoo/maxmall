package com.maxmall.provider.marketing.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "优惠券新增参数")
public class CouponModifyDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * NAME.
     */
    @NotNull
    @Length(max = 64)
    @ApiModelProperty(value = "优惠券名称")
    private String name;

    /**
     * 备注.
     */
    @Length(max = 255)
    @ApiModelProperty(value = "优惠券备注")
    private String description;

    /**
     * 优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券.
     */
    @NotNull
    @ApiModelProperty(value = "优惠卷类型")
    private Integer type;

    /**
     * 使用类型：0->全场通用；1->指定分类；2->指定商品.
     */
    @NotNull
    @ApiModelProperty(value = "使用类型")
    private Integer useType;

    /**
     * 领取后几天.
     */
    @ApiModelProperty(value = "领取后几天")
    private Integer afterDay;

    /**
     * 每人限领张数.
     */
    @ApiModelProperty(value = "每人限领张数")
    private Integer perLimit;

    /**
     * 使用计时方式（0:固定时间；1:领取后几天）.
     */
    @NotNull
    @ApiModelProperty(value = "使用计时方式")
    private Integer startType;

    /**
     * 发行数量.
     */
    @ApiModelProperty(value = "发行数量")
    private Integer publishCount;

    /**
     * 领取数量.
     */
    @ApiModelProperty(value = "领取数量")
    private Integer receiveCount;

    /**
     * 使用结束时间.
     */
    @ApiModelProperty(value = "使用结束时间")
    private Date endTime;

    /**
     * 使用开始时间.
     */
    @ApiModelProperty(value = "使用开始时间")
    private Date startTime;

    /**
     * 可以领取的日期.
     */
    @ApiModelProperty(value = "可以领取的日期")
    private Date enableTime;

    /**
     * 金额.
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    /**
     * 使用门槛；0表示无门槛.
     */
    @ApiModelProperty(value = "使用门槛")
    private BigDecimal minPoint;

    @ApiModelProperty(value = "优惠券关联")
    private List<CouponRelationDto> relationList;

}
