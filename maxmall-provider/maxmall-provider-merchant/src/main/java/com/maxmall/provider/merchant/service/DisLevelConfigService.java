package com.maxmall.provider.merchant.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.merchant.model.domain.merchant.DistributorLevelConfigDO;
import com.maxmall.provider.merchant.model.dto.merchant.DisLevelModifyDto;
import com.maxmall.provider.merchant.model.dto.merchant.DisLevelQueryDto;
import com.maxmall.provider.merchant.model.vo.DistributorLevelConfigVo;

import java.util.List;

public interface DisLevelConfigService extends IService<DistributorLevelConfigDO> {

    /**
     * 获取全部等级
     * @param loginAuthDto
     * @return
     */
    List<DistributorLevelConfigVo> listAllLevel(UserTokenDto loginAuthDto);

    /**
     * 分页查询等级列表
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<DistributorLevelConfigVo> queryLevelListWithPage(DisLevelQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 创建或更新等级配置
     * @param levelModifyDto
     * @param loginAuthDto
     * @return
     */
    int saveOrModifyLevel(DisLevelModifyDto levelModifyDto, UserTokenDto loginAuthDto);

    /**
     * 删除等级
     * @param id
     * @param loginAuthDto
     * @return
     */
    int deleteLevel(Long id, UserTokenDto loginAuthDto);

    /**
     * 等级详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    DistributorLevelConfigVo levelDetail(Long id, UserTokenDto loginAuthDto);
}
