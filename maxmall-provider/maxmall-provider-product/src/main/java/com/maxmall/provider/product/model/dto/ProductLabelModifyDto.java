package com.maxmall.provider.product.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品新增参数
 * Created by maxmall on 2018/4/26.
 */
@Data
@ApiModel(value = "商品创建参数")
public class ProductLabelModifyDto {

    @NotNull
    private Long id;

    /**
     * label状态.
     */
    private Integer status;

    /**
     * label属性.
     */
    private String type;

}
