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
@ApiModel(value = "用户分页查询Dto ")
public class QueryUserDto implements Serializable {

    /**
     * 用户名.
     */
    @Length(max = 32)
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 登录账户名.
     */
    @Length(max = 32)
    @ApiModelProperty(value = "登录账户名")
    private String loginName;

    /**
     * 用户手机.
     */
    @Length(max = 16)
    @ApiModelProperty(value = "用户手机")
    private String phone;

    /**
     * STATUS.
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 来源.
     */
    @ApiModelProperty(value = "来源")
    private Integer userSource;

    private Integer pageNum;

    private Integer pageSize;

    @Override
    public String toString() {
        return "QueryUserDto{" +
                "userName='" + userName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", userSource=" + userSource +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
