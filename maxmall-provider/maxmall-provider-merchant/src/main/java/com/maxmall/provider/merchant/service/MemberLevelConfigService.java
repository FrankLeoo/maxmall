package com.maxmall.provider.merchant.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.merchant.model.domain.merchant.MemberLevelConfigDO;
import com.maxmall.provider.merchant.model.dto.merchant.MemberLevelModifyDto;
import com.maxmall.provider.merchant.model.dto.merchant.MemberLevelQueryDto;
import com.maxmall.provider.merchant.model.vo.MemberLevelConfigVo;

import java.util.List;

public interface MemberLevelConfigService extends IService<MemberLevelConfigDO> {

    /**
     * 获取全部等级
     * @param loginAuthDto
     * @return
     */
    List<MemberLevelConfigVo> listAllLevel(UserTokenDto loginAuthDto);

    /**
     * 分页查询等级列表
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<MemberLevelConfigVo> queryLevelListWithPage(MemberLevelQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 创建或更新等级配置
     * @param levelModifyDto
     * @param loginAuthDto
     * @return
     */
    int saveOrModifyLevel(MemberLevelModifyDto levelModifyDto, UserTokenDto loginAuthDto);

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
    MemberLevelConfigVo levelDetail(Long id, UserTokenDto loginAuthDto);
}
