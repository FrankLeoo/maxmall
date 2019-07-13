package com.maxmall.provider.merchant.service.impl;

import com.maxmall.common.core.support.BaseService;
import com.maxmall.provider.merchant.mapper.merchant.MerchantAuditDOMapper;
import com.maxmall.provider.merchant.model.domain.merchant.MerchantAuditDO;
import com.maxmall.provider.merchant.service.MerchantAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ivoter
 * @ClassName MenuServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 角色实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MerchantAuditServiceImpl extends BaseService<MerchantAuditDO> implements MerchantAuditService {

    @Autowired
    private MerchantAuditDOMapper merchantAuditDOMapper;


}
