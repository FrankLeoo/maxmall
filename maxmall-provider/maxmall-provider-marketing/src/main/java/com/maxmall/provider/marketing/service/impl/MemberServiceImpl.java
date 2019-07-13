package com.maxmall.provider.marketing.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.enums.CommonStatusEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.core.utils.PermissionCheckUtil;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.marketing.exception.MarketingBizException;
import com.maxmall.provider.marketing.mapper.member.*;
import com.maxmall.provider.marketing.model.domain.member.*;
import com.maxmall.provider.marketing.model.dto.MemberAddressQueryDto;
import com.maxmall.provider.marketing.model.dto.MemberGrowthQueryDto;
import com.maxmall.provider.marketing.model.dto.MemberIntegrationQueryDto;
import com.maxmall.provider.marketing.model.dto.MemberQueryDto;
import com.maxmall.provider.marketing.model.vo.*;
import com.maxmall.provider.marketing.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ivoter
 * @ClassName MemberServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 会员service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl extends BaseService<MemberDO> implements MemberService {

    @Autowired
    private MemberDOMapper memberDOMapper;
    @Autowired
    private MemberAccountDOMapper memberAccountDOMapper;
    @Autowired
    private MemberGrowthHistoryDOMapper memberGrowthHistoryDOMapper;
    @Autowired
    private MemberIntegrationHistoryDOMapper memberIntegrationHistoryDOMapper;
    @Autowired
    private MemberAddressDOMapper memberAddressDOMapper;

    /**
     * 删除禁用会员
     * @param id
     * @param loginAuthDto
     * @return
     */
    @Override
    public int deleteMember(Long id, UserTokenDto loginAuthDto) {
        MemberDO memberDO = memberDOMapper.selectByPrimaryKey(id);
        if (memberDO == null){
            throw new MarketingBizException(ErrorCodeEnum.UAC10011003,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(memberDO.getMerchantId(),loginAuthDto);

        MemberDO memberEntity = new MemberDO();
        memberEntity.setId(memberDO.getId());
        memberEntity.setStatus(CommonStatusEnum.DISABLE.getStatus());

        return memberDOMapper.updateByPrimaryKeySelective(memberEntity);
    }

    @Override
    public PageInfo<MemberVo> queryMemberListWithPage(MemberQueryDto queryParam, UserTokenDto loginAuthDto) {
        Integer pageNum = queryParam.getPageNum();

        Integer pageSize = queryParam.getPageSize();
        if (pageNum ==null || pageNum <=0){
            pageNum = 1;
        }
        if (pageSize ==null || pageSize <=0 || pageSize>100){
            pageSize = 20;
        }
        PageHelper.startPage(pageNum, pageSize);
        //查询参数
        Example example = new Example(MemberDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus());

        if (StringUtils.isNotEmpty(queryParam.getNickname())){
            criteria.andLike("nickname","%"+queryParam.getNickname()+"%");
        }
        List<MemberDO> uacUserList = memberDOMapper.selectByExample(example);
        PageInfo<MemberDO> pageInfo = new PageInfo<>(uacUserList);

        //转化
        List<MemberVo> responses = BeanConverter.batchConvert(uacUserList,MemberVo.class);
        PageInfo<MemberVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    /**
     * 查询会员详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    @Override
    public MemberVo memberDetail(Long id, UserTokenDto loginAuthDto) {
        MemberDO memberDO = memberDOMapper.selectByPrimaryKey(id);
        if (memberDO == null){
            throw new MarketingBizException(ErrorCodeEnum.UAC10011003,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(memberDO.getMerchantId(),loginAuthDto);
        MemberVo memberVo = BeanConverter.convert(memberDO,MemberVo.class);

        MemberAccountDO param = new MemberAccountDO();
        param.setMemberId(id);
        MemberAccountDO accountDO = memberAccountDOMapper.selectOne(param);

        if (accountDO != null){
            MemberAccountVo accountVo = BeanConverter.convert(accountDO,MemberAccountVo.class);
            memberVo.setAccount(accountVo);
        }
        //查询account统计
        return memberVo;
    }

    /**
     * 查询会员成长值记录分页
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    @Override
    public PageInfo<MemberGrowthVo> queryGrowthListWithPage(MemberGrowthQueryDto queryParam, UserTokenDto loginAuthDto) {
        Integer pageNum = queryParam.getPageNum();

        Integer pageSize = queryParam.getPageSize();
        if (pageNum ==null || pageNum <=0){
            pageNum = 1;
        }
        if (pageSize ==null || pageSize <=0 || pageSize>100){
            pageSize = 20;
        }
        PageHelper.startPage(pageNum, pageSize);
        //查询参数
        Example example = new Example(MemberGrowthHistoryDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus()).andEqualTo("memberId",queryParam.getMemberId());

        List<MemberGrowthHistoryDO> growthHistoryDOS = memberGrowthHistoryDOMapper.selectByExample(example);
        PageInfo<MemberGrowthHistoryDO> pageInfo = new PageInfo<>(growthHistoryDOS);

        //转化
        List<MemberGrowthVo> responses = BeanConverter.batchConvert(growthHistoryDOS,MemberGrowthVo.class);
        PageInfo<MemberGrowthVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    @Override
    public PageInfo<MemberIntegrationVo> queryIntegrationListWithPage(MemberIntegrationQueryDto queryParam, UserTokenDto loginAuthDto) {
        Integer pageNum = queryParam.getPageNum();

        Integer pageSize = queryParam.getPageSize();
        if (pageNum ==null || pageNum <=0){
            pageNum = 1;
        }
        if (pageSize ==null || pageSize <=0 || pageSize>100){
            pageSize = 20;
        }
        PageHelper.startPage(pageNum, pageSize);
        //查询参数
        Example example = new Example(MemberIntegrationHistoryDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus()).andEqualTo("memberId",queryParam.getMemberId());

        List<MemberIntegrationHistoryDO> growthHistoryDOS = memberIntegrationHistoryDOMapper.selectByExample(example);
        PageInfo<MemberIntegrationHistoryDO> pageInfo = new PageInfo<>(growthHistoryDOS);

        //转化
        List<MemberIntegrationVo> responses = BeanConverter.batchConvert(growthHistoryDOS,MemberIntegrationVo.class);
        PageInfo<MemberIntegrationVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    /**
     * 会员地址列表
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    @Override
    public PageInfo<MemberAddressVo> queryAddressListWithPage(MemberAddressQueryDto queryParam, UserTokenDto loginAuthDto) {
        Integer pageNum = queryParam.getPageNum();

        Integer pageSize = queryParam.getPageSize();
        if (pageNum ==null || pageNum <=0){
            pageNum = 1;
        }
        if (pageSize ==null || pageSize <=0 || pageSize>100){
            pageSize = 20;
        }
        PageHelper.startPage(pageNum, pageSize);
        //查询参数
        Example example = new Example(MemberAddressDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus()).andEqualTo("memberId",queryParam.getMemberId());

        List<MemberAddressDO> addressDOS = memberAddressDOMapper.selectByExample(example);
        PageInfo<MemberAddressDO> pageInfo = new PageInfo<>(addressDOS);

        //转化
        List<MemberAddressVo> responses = BeanConverter.batchConvert(addressDOS,MemberAddressVo.class);
        PageInfo<MemberAddressVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }
}
