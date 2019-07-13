
package com.maxmall.provider.product.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * The class Product req dto.
 *
 * @author maxmall.net@gmail.com
 */
@Data
@ApiModel(value = "商品查询参数")
public class ProductReqDto implements Serializable {
	private static final long serialVersionUID = -9070173642901418263L;

	@NotNull
	@ApiModelProperty(value = "商品id列表")
	private List<Long> ids;

	@NotNull
	@ApiModelProperty(value = "商户ID")
	private Long merchantId;
}
