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
import com.maxmall.provider.marketing.mapper.market.CouponDOMapper;
import com.maxmall.provider.marketing.model.domain.market.CouponDO;
import com.maxmall.provider.marketing.model.domain.market.CouponRelationDO;
import com.maxmall.provider.marketing.model.dto.CouponModifyDto;
import com.maxmall.provider.marketing.model.dto.CouponQueryDto;
import com.maxmall.provider.marketing.model.enums.CouponUseTypeEnum;
import com.maxmall.provider.marketing.model.vo.CouponRelationVo;
import com.maxmall.provider.marketing.model.vo.CouponVo;
import com.maxmall.provider.marketing.service.CouponRelationService;
import com.maxmall.provider.marketing.service.CouponService;
import com.maxmall.zk.generator.IncrementIdGenerator;
import com.maxmall.zk.generator.UniqueIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ivoter
 * @ClassName CouponServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 优惠券service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CouponServiceImpl extends BaseService<CouponDO> implements CouponService {

    @Autowired
    private CouponDOMapper couponDOMapper;
    @Autowired
    private CouponRelationService couponRelationService;

    /**
     * 删除优惠券
     * 物理删除优惠券，防止商品过滤优惠券得到失效优惠活动
     * @param id
     * @param loginAuthDto
     * @return
     */
    @Override
    public int deleteCoupon(Long id, UserTokenDto loginAuthDto) {
        CouponDO couponDO = couponDOMapper.selectByPrimaryKey(id);
        if (couponDO == null){
            throw new MarketingBizException(ErrorCodeEnum.TPC100600001,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(couponDO.getMerchantId(),loginAuthDto);
        int result = couponDOMapper.delete(couponDO);

        //删除关联
        CouponRelationDO param = new CouponRelationDO();
        param.setCouponId(couponDO.getId());

        couponRelationService.delete(param);
        return result;
    }

    /**
     * 添加或修改优惠券
     * @param couponParam
     * @param loginAuthDto
     * @return
     */
    @Override
    public int saveOrModifyCoupon(CouponModifyDto couponParam, UserTokenDto loginAuthDto) {
        Long id = couponParam.getId();
        CouponDO couponEntity = BeanConverter.convert(couponParam,CouponDO.class);
        couponEntity.setUpdateInfo(loginAuthDto);

        int result = 0;
        if (id != null){
            CouponDO couponDO = couponDOMapper.selectByPrimaryKey(id);
            if (couponDO == null){
                throw new MarketingBizException(ErrorCodeEnum.TPC100600001,id);
            }
            PermissionCheckUtil.checkDataMerchantPermission(couponDO.getMerchantId(),loginAuthDto);

            result = couponDOMapper.updateByPrimaryKeySelective(couponEntity);
            CouponRelationDO param = new CouponRelationDO();
            param.setCouponId(couponDO.getId());

            couponRelationService.delete(param);
        }else{
            Long code = UniqueIdGenerator.getInstance(IncrementIdGenerator.getServiceId()).nextId();
            couponEntity.setMerchantId(loginAuthDto.getMerchantId());
            couponEntity.setStatus(CommonStatusEnum.ENABLE.getStatus());
            couponEntity.setCode(String.valueOf(code));
            couponEntity.setUseCount(0);
            couponEntity.setReceiveCount(0);

            result =couponDOMapper.insert(couponEntity);
        }
        //保存关联关系
        if (CollectionUtils.isNotEmpty(couponParam.getRelationList())){
            List<CouponRelationDO> relationDOS = couponParam.getRelationList().stream().map(item -> {
                CouponRelationDO relationDO = BeanConverter.convert(item, CouponRelationDO.class);
                relationDO.setCouponId(couponEntity.getId());
                relationDO.setMerchantId(loginAuthDto.getMerchantId());

                return relationDO;
            }).collect(Collectors.toList());

            couponRelationService.batchSave(relationDOS);
        }

        return result;
    }

    @Override
    public PageInfo<CouponVo> queryCouponListWithPage(CouponQueryDto queryParam, UserTokenDto loginAuthDto) {
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
        Example example = new Example(CouponDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus()).andEqualTo("type",queryParam.getType())
                .andEqualTo("useType",queryParam.getUseType());

        if (StringUtils.isNotEmpty(queryParam.getName())){
            criteria.andLike("name","%"+queryParam.getName()+"%");
        }
        List<CouponDO> uacUserList = couponDOMapper.selectByExample(example);
        PageInfo<CouponDO> pageInfo = new PageInfo<>(uacUserList);

        //转化
        List<CouponVo> responses = BeanConverter.batchConvert(uacUserList,CouponVo.class);
        PageInfo<CouponVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    /**
     * 查询详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    @Override
    public CouponVo couponDetail(Long id, UserTokenDto loginAuthDto) {
        CouponDO couponDO = couponDOMapper.selectByPrimaryKey(id);
        if (couponDO == null){
            throw new MarketingBizException(ErrorCodeEnum.TPC100600001,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(couponDO.getMerchantId(),loginAuthDto);
        CouponVo couponVo = BeanConverter.convert(couponDO,CouponVo.class);

        CouponUseTypeEnum typeEnum = CouponUseTypeEnum.getByType(couponDO.getUseType());
        if (CouponUseTypeEnum.ALL != typeEnum){
            CouponRelationDO param = new CouponRelationDO();
            param.setCouponId(couponDO.getId());
            List<CouponRelationDO> relationDOS = couponRelationService.select(param);

            List<CouponRelationVo> relationVoList = BeanConverter.batchConvert(relationDOS,CouponRelationVo.class);
            couponVo.setRelationList(relationVoList);
        }

        return couponVo;
    }

    @Override
    public List<CouponVo> couponListAll(UserTokenDto loginAuthDto, CouponQueryDto queryParam) {
        //查询参数
        Example example = new Example(CouponDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus()).andEqualTo("type",queryParam.getType())
                .andEqualTo("useType",queryParam.getUseType());

        if (StringUtils.isNotEmpty(queryParam.getName())){
            criteria.andLike("name","%"+queryParam.getName()+"%");
        }
        List<CouponDO> uacUserList = couponDOMapper.selectByExample(example);
        //转化
        List<CouponVo> responses = BeanConverter.batchConvert(uacUserList,CouponVo.class);

        return responses;
    }

}
