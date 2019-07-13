package com.maxmall.provider.merchant.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * The class Modify user status dto.
 *
 * @author maxmall.net@gmail.com
 */
@Data
@ApiModel(value = "角色分页查询Dto ")
public class QueryRoleDto implements Serializable {

    /**
     * 用户名.
     */
    @Length(max = 32)
    @ApiModelProperty(value = "角色编码")
    private String code;

    /**
     * 登录账户名.
     */
    @Length(max = 32)
    @ApiModelProperty(value = "角色名称")
    private String name;

    private Integer pageNum;

    private Integer pageSize;

    @Override
    public String toString() {
        return "QueryRoleDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
