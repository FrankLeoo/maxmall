package com.maxmall.provider.product.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 品牌传递参数
 * Created by maxmall on 2018/4/26.
 */
@Data
@ApiModel(value = "分类显示参数")
public class CategoryShowStatusDto {

    @NotNull
    @NotEmpty
    private List<Long> ids;

    /**
     * 显示状态.
     */
    @NotNull
    @ApiModelProperty(value = "显示状态")
    private Integer showStatus;


}
