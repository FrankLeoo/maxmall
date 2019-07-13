package com.maxmall.provider.marketing.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "活动新增参数")
public class PromotionModifyDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 赠品id.
     */
    @ApiModelProperty(value = "赠品id")
    private Long giftId;

    /**
     * 活动名称.
     */
    @NotNull
    @Length(max = 64)
    @ApiModelProperty(value = "活动名称")
    private String name;

    /**
     * 使用类型：0->全场通用；1->指定分类；2->指定商品.
     */
    @NotNull
    @ApiModelProperty(value = "使用类型")
    private Integer useType;

    /**
     * 赠品图片.
     */
    @ApiModelProperty(value = "赠品图片")
    private String giftPic;

    /**
     * 赠品名称.
     */
    @ApiModelProperty(value = "赠品名称")
    private String giftName;

    /**
     * 活动类型；0->满减；1->满赠；2->限时优惠；3->赠品.
     */
    @NotNull
    @ApiModelProperty(value = "活动类型")
    private Integer type;

    /**
     * 限时折扣.
     */
    @Column(name = "discount")
    private Integer discount;

    /**
     * 赠品类型（0:商品 1:优惠券）.
     */
    @Column(name = "gift_type")
    private Integer giftType;

    /**
     * 结束日期.
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 开始日期.
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 满金额.
     */
    @Column(name = "full_price")
    private BigDecimal fullPrice;

    /**
     * 减金额.
     */
    @Column(name = "reduce_price")
    private BigDecimal reducePrice;

    @ApiModelProperty(value = "活动关联")
    private List<PromotionRelationDto> relationList;
}
