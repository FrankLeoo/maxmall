package com.maxmall.common.security.authorize.demain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ivoter
 * @ClassName AuthorAccessToken.java
 * @date 2019/04/28 15:18:00
 * @Description 返回token信息
 */
@Data
public class AuthorAccessToken implements Serializable {

    private String access_token;
    private String refresh_token;

    private String userName;
    private String icon;

}
