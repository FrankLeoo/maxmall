package com.maxmall.provider.merchant.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.merchant.model.domain.merchant.DistrCommissionConfigDO;
import com.maxmall.provider.merchant.model.dto.merchant.DisCommissionModifyDto;
import com.maxmall.provider.merchant.model.dto.merchant.DisCommissionQueryDto;
import com.maxmall.provider.merchant.model.vo.DisCommissionConfigVo;

import java.util.List;

public interface DisCommissionConfigService extends IService<DistrCommissionConfigDO> {

    /**
     * 获取全部等级
     * @param loginAuthDto
     * @return
     */
    List<DisCommissionConfigVo> listAllCommission(UserTokenDto loginAuthDto);

    /**
     * 分页查询等级列表
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<DisCommissionConfigVo> queryCommissionListWithPage(DisCommissionQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 创建或更新等级配置
     * @param commissionModifyDto
     * @param loginAuthDto
     * @return
     */
    int saveOrModifyCommission(DisCommissionModifyDto commissionModifyDto, UserTokenDto loginAuthDto);

    /**
     * 删除等级
     * @param id
     * @param loginAuthDto
     * @return
     */
    int deleteCommission(Long id, UserTokenDto loginAuthDto);

    /**
     * 等级详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    DisCommissionConfigVo commissionDetail(Long id, UserTokenDto loginAuthDto);
}
