package com.maxmall.provider.merchant.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.merchant.model.domain.merchant.DistributorAuditDO;
import com.maxmall.provider.merchant.model.dto.distributor.ModifyDistributorDto;
import com.maxmall.provider.merchant.model.dto.distributor.QueryDistrbutorAuditDto;
import com.maxmall.provider.merchant.model.vo.DistributorAuditVo;

/**
 * @author ivoter
 * @ClassName AccountService.java
 * @date 2019/05/21 17:18:00
 * @Description 分销商审核管理
 */
public interface DistributorAuditService extends IService<DistributorAuditDO> {

    /**
     * 分页查询分销商申请
     *
     * @param queryDistrbutorDto
     * @param loginAuthDto
     * @return
     */
    PageInfo<DistributorAuditVo> queryAuditListWithPage(QueryDistrbutorAuditDto queryDistrbutorDto, UserTokenDto loginAuthDto);

    /**
     * 审核分销员申请
     *
     * @param modifyDistributorDto
     * @param loginAuthDto
     * @return
     */
    int auditDistributor(ModifyDistributorDto modifyDistributorDto, UserTokenDto loginAuthDto);
}
