package com.maxmall.provider.marketing.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.enums.CommonStatusEnum;
import com.maxmall.common.core.enums.TrueOrFalseEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.core.utils.DateUtil;
import com.maxmall.common.core.utils.PermissionCheckUtil;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.marketing.exception.MarketingBizException;
import com.maxmall.provider.marketing.mapper.market.CouponDOMapper;
import com.maxmall.provider.marketing.mapper.market.MemberCouponDOMapper;
import com.maxmall.provider.marketing.mapper.member.MemberDOMapper;
import com.maxmall.provider.marketing.model.domain.market.CouponDO;
import com.maxmall.provider.marketing.model.domain.market.MemberCouponDO;
import com.maxmall.provider.marketing.model.domain.member.MemberDO;
import com.maxmall.provider.marketing.model.dto.MemberCouponGiveDto;
import com.maxmall.provider.marketing.model.dto.MemberCouponQueryDto;
import com.maxmall.provider.marketing.model.enums.CouponStartTypeEnum;
import com.maxmall.provider.marketing.model.vo.MemberCouponVo;
import com.maxmall.provider.marketing.service.MemberCouponService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author ivoter
 * @ClassName OrderOperateHistoryServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 会员优惠券service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberCouponServiceImpl extends BaseService<MemberCouponDO> implements MemberCouponService {

    @Autowired
    private MemberCouponDOMapper memberCouponDOMapper;
    @Autowired
    private CouponDOMapper couponDOMapper;
    @Autowired
    private MemberDOMapper memberDOMapper;

    /**
     * 分页获取优惠券
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    @Override
    public PageInfo<MemberCouponVo> queryCouponListWithPage(MemberCouponQueryDto queryParam, UserTokenDto loginAuthDto) {
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
        Example example = new Example(MemberCouponDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus()).andEqualTo("type",queryParam.getType())
                .andEqualTo("couponId",queryParam.getCouponId()).andEqualTo("memberId",queryParam.getMemberId());

        if (StringUtils.isNotEmpty(queryParam.getName())){
            criteria.andLike("name","%"+queryParam.getName()+"%");
        }
        if (StringUtils.isNotEmpty(queryParam.getCode())){
            criteria.andLike("code","%"+queryParam.getCode()+"%");
        }
        if (StringUtils.isNotEmpty(queryParam.getMemberName())){
            criteria.andLike("memberName","%"+queryParam.getMemberName()+"%");
        }
        if (StringUtils.isNotEmpty(queryParam.getUsedSource())){
            criteria.andLike("usedSource","%"+queryParam.getUsedSource()+"%");
        }
        List<MemberCouponDO> resultList = memberCouponDOMapper.selectByExample(example);
        PageInfo<MemberCouponDO> pageInfo = new PageInfo<>(resultList);

        //转化
        List<MemberCouponVo> responses = BeanConverter.batchConvert(resultList,MemberCouponVo.class);
        PageInfo<MemberCouponVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    /**
     * 给会员赠送优惠券
     * @param couponParam
     * @param loginAuthDto
     * @return
     */
    @Override
    public int giveCoupon(MemberCouponGiveDto couponParam, UserTokenDto loginAuthDto) {
        CouponDO couponDO = couponDOMapper.selectByPrimaryKey(couponParam.getCouponId());
        if (couponDO == null || couponDO.getStatus() != CommonStatusEnum.ENABLE.getStatus()){
            throw new MarketingBizException(ErrorCodeEnum.TPC100600001,couponParam.getCouponId());
        }
        PermissionCheckUtil.checkDataMerchantPermission(couponDO.getMerchantId(),loginAuthDto);
        MemberDO memberDO = memberDOMapper.selectByPrimaryKey(couponParam.getMemberId());
        if (memberDO == null){
            throw new MarketingBizException(ErrorCodeEnum.UAC10011002,couponParam.getMemberId());
        }
        PermissionCheckUtil.checkDataMerchantPermission(memberDO.getMerchantId(),loginAuthDto);

        MemberCouponDO entity = new MemberCouponDO();
        entity.setCouponId(couponDO.getId());
        entity.setMemberId(memberDO.getId());
        entity.setMemberId(loginAuthDto.getMerchantId());
        entity.setCode(couponDO.getCode());
        entity.setName(couponDO.getName());
        entity.setMemberIcon(memberDO.getIcon());
        entity.setMemberName(memberDO.getNickname());
        entity.setType(couponDO.getType());
        entity.setIsUsed(TrueOrFalseEnum.FALSE.getStatus());
        entity.setStatus(CommonStatusEnum.ENABLE.getStatus());
        entity.setUseType(couponDO.getUseType());
        entity.setAmount(couponDO.getAmount());
        entity.setMinPoint(couponDO.getMinPoint());

        CouponStartTypeEnum typeEnum = CouponStartTypeEnum.getByType(couponDO.getStartType());
        if (CouponStartTypeEnum.AFTER_GIVE == typeEnum){
            Date start = new Date();
            entity.setStartTime(start);
            entity.setEndTime(DateUtil.getDateAfterDay(start,couponDO.getAfterDay()));
        }

        return memberCouponDOMapper.insert(entity);
    }
}
