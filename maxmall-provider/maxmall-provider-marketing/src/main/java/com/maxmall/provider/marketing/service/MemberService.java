package com.maxmall.provider.marketing.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.marketing.model.domain.member.MemberDO;
import com.maxmall.provider.marketing.model.dto.*;
import com.maxmall.provider.marketing.model.vo.MemberAddressVo;
import com.maxmall.provider.marketing.model.vo.MemberGrowthVo;
import com.maxmall.provider.marketing.model.vo.MemberIntegrationVo;
import com.maxmall.provider.marketing.model.vo.MemberVo;

/**
 * @author ivoter
 * @ClassName MemberService.java
 * @date 2019/05/21 17:18:00
 * @Description 会员service
 */
public interface MemberService extends IService<MemberDO> {

    /**
     * 删除禁用会员
     * @param id
     * @param loginAuthDto
     * @return
     */
    int deleteMember(Long id, UserTokenDto loginAuthDto);

    /**
     * 分页获取用户列表
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<MemberVo> queryMemberListWithPage(MemberQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 会员详情接口
     * @param id
     * @param loginAuthDto
     * @return
     */
    MemberVo memberDetail(Long id, UserTokenDto loginAuthDto);

    /**
     * 分页查询用户成长值
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<MemberGrowthVo> queryGrowthListWithPage(MemberGrowthQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 分页查询会员积分
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<MemberIntegrationVo> queryIntegrationListWithPage(MemberIntegrationQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 会员地址列表
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<MemberAddressVo> queryAddressListWithPage(MemberAddressQueryDto queryParam, UserTokenDto loginAuthDto);
}
