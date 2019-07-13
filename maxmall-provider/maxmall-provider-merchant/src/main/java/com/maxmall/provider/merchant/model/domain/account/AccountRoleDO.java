package com.maxmall.provider.merchant.model.domain.account;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The table SSC_ACCOUNT_ROLE
 */
@Data
@Table(name = "ssc_account_role")
public class AccountRoleDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色id.
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 用户id.
     */
    @Column(name = "user_id")
    private Long userId;

}
