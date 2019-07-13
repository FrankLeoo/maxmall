package com.maxmall.provider.merchant.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.merchant.model.domain.merchant.DistributorDO;
import com.maxmall.provider.merchant.model.dto.distributor.DistributorRadioDto;
import com.maxmall.provider.merchant.model.dto.distributor.QueryDistrHistoryDto;
import com.maxmall.provider.merchant.model.dto.distributor.QueryDistrbutorDto;
import com.maxmall.provider.merchant.model.vo.DistrMoneyHistoryVo;
import com.maxmall.provider.merchant.model.vo.DistributorVo;

import java.util.List;

/**
 * @author ivoter
 * @ClassName AccountService.java
 * @date 2019/05/21 17:18:00
 * @Description 分销商管理
 */
public interface DistributorService extends IService<DistributorDO> {

    /**
     * 查询分销商分页数据
     * @param queryDistrbutorDto
     * @param loginAuthDto
     * @return
     */
    PageInfo<DistributorVo> queryDistributorListWithPage(QueryDistrbutorDto queryDistrbutorDto, UserTokenDto loginAuthDto);

    /**
     * 根据ID查询分销商详情
     * @param distributorId
     * @param loginAuthDto
     * @return
     */
    DistributorVo queryByDistributorId(Long distributorId, UserTokenDto loginAuthDto);

    /**
     * 根据Id修改分销商分销比例.
     * @param distributorRadioDto
     * @param loginAuthDto
     * @return
     */
    Integer modifySalesRatio(DistributorRadioDto distributorRadioDto, UserTokenDto loginAuthDto);

    /**
     * 全部分销员
     * @param loginAuthDto
     * @return
     */
    List<DistributorVo> distributorListAll(UserTokenDto loginAuthDto);

    /**
     * 分销商操作记录
     * @param queryDistrHistoryDto
     * @param loginAuthDto
     * @return
     */
    PageInfo<DistrMoneyHistoryVo> queryHistoryListWithPage(QueryDistrHistoryDto queryDistrHistoryDto, UserTokenDto loginAuthDto);
}
