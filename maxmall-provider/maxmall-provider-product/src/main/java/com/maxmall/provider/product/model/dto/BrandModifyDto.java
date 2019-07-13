package com.maxmall.provider.product.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * 品牌传递参数
 * Created by maxmall on 2018/4/26.
 */
@Data
@ApiModel(value = "品牌创建参数")
public class BrandModifyDto {

    private Long id;

    @ApiModelProperty(value = "中文品牌名称",required = true)
    @NotEmpty(message = "名称不能为空")
    @Length(max = 64)
    private String name;

    @ApiModelProperty(value = "英文品牌名称")
    @Column(name = "en_name")
    @Length(max = 64)
    private String enName;

    @ApiModelProperty(value = "品牌首字母")
    @Length(max = 16)
    private String firstLetter;

    @ApiModelProperty(value = "排序字段")
    @Min(value = 0, message = "排序最小为0")
    private Integer sort;

    @ApiModelProperty(value = "品牌logo",required = true)
    @NotEmpty(message = "品牌logo不能为空")
    @Length(max = 255)
    private String logo;

    @ApiModelProperty(value = "品牌故事")
    @Length(max = 500)
    private String description;

}
