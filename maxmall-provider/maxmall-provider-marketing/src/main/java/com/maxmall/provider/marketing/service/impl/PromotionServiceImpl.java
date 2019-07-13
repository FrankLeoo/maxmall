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
import com.maxmall.provider.marketing.mapper.market.PromotionDOMapper;
import com.maxmall.provider.marketing.model.domain.market.CouponDO;
import com.maxmall.provider.marketing.model.domain.market.CouponRelationDO;
import com.maxmall.provider.marketing.model.domain.market.PromotionDO;
import com.maxmall.provider.marketing.model.domain.market.PromotionRelationDO;
import com.maxmall.provider.marketing.model.dto.PromotionModifyDto;
import com.maxmall.provider.marketing.model.dto.PromotionQueryDto;
import com.maxmall.provider.marketing.model.enums.CouponUseTypeEnum;
import com.maxmall.provider.marketing.model.enums.PromotionUseTypeEnum;
import com.maxmall.provider.marketing.model.vo.CouponRelationVo;
import com.maxmall.provider.marketing.model.vo.CouponVo;
import com.maxmall.provider.marketing.model.vo.PromotionRelationVo;
import com.maxmall.provider.marketing.model.vo.PromotionVo;
import com.maxmall.provider.marketing.service.PromotionRelationService;
import com.maxmall.provider.marketing.service.PromotionService;
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
 * @ClassName OrderOperateHistoryServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 活动service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PromotionServiceImpl extends BaseService<PromotionDO> implements PromotionService {

    @Autowired
    private PromotionDOMapper promotionDOMapper;
    @Autowired
    private PromotionRelationService promotionRelationService;

    /**
     * 添加或更新优惠券
     * @param promotionParam
     * @param loginAuthDto
     * @return
     */
    @Override
    public int saveOrModifyPromotion(PromotionModifyDto promotionParam, UserTokenDto loginAuthDto) {

        Long id = promotionParam.getId();
        PromotionDO promotionEntity = BeanConverter.convert(promotionParam,PromotionDO.class);
        promotionEntity.setUpdateInfo(loginAuthDto);

        int result = 0;
        if (id != null){
            PromotionDO promotionDO = promotionDOMapper.selectByPrimaryKey(id);
            if (promotionDO == null){
                throw new MarketingBizException(ErrorCodeEnum.TPC100600003,id);
            }
            PermissionCheckUtil.checkDataMerchantPermission(promotionDO.getMerchantId(),loginAuthDto);

            result = promotionDOMapper.updateByPrimaryKeySelective(promotionEntity);
            PromotionRelationDO param = new PromotionRelationDO();
            param.setPromotionId(promotionDO.getId());

            promotionRelationService.delete(param);
        }else{
            promotionEntity.setMerchantId(loginAuthDto.getMerchantId());
            promotionEntity.setStatus(CommonStatusEnum.ENABLE.getStatus());
            result =promotionDOMapper.insert(promotionEntity);
        }
        //保存关联关系
        if (CollectionUtils.isNotEmpty(promotionParam.getRelationList())){
            List<PromotionRelationDO> relationDOS = promotionParam.getRelationList().stream().map(item -> {
                PromotionRelationDO relationDO = BeanConverter.convert(item, PromotionRelationDO.class);
                relationDO.setPromotionId(promotionEntity.getId());
                relationDO.setMerchantId(loginAuthDto.getMerchantId());

                return relationDO;
            }).collect(Collectors.toList());

            promotionRelationService.batchSave(relationDOS);
        }

        return result;
    }

    @Override
    public int deletePromotion(Long id, UserTokenDto loginAuthDto) {

        PromotionDO promotionDO = promotionDOMapper.selectByPrimaryKey(id);
        if (promotionDO == null){
            throw new MarketingBizException(ErrorCodeEnum.TPC100600003,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(promotionDO.getMerchantId(),loginAuthDto);
        int result = promotionDOMapper.delete(promotionDO);

        //删除关联
        PromotionRelationDO param = new PromotionRelationDO();
        param.setPromotionId(promotionDO.getId());

        promotionRelationService.delete(param);
        return result;
    }

    @Override
    public PageInfo<PromotionVo> queryPromotionListWithPage(PromotionQueryDto queryParam, UserTokenDto loginAuthDto) {
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
        Example example = new Example(PromotionDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus()).andEqualTo("type",queryParam.getType());

        if (StringUtils.isNotEmpty(queryParam.getName())){
            criteria.andLike("name","%"+queryParam.getName()+"%");
        }
        List<PromotionDO> uacUserList = promotionDOMapper.selectByExample(example);
        PageInfo<PromotionDO> pageInfo = new PageInfo<>(uacUserList);

        //转化
        List<PromotionVo> responses = BeanConverter.batchConvert(uacUserList,PromotionVo.class);
        PageInfo<PromotionVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    /**
     * 活动详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    @Override
    public PromotionVo promotionDetail(Long id, UserTokenDto loginAuthDto) {
        PromotionDO promotionDO = promotionDOMapper.selectByPrimaryKey(id);
        if (promotionDO == null){
            throw new MarketingBizException(ErrorCodeEnum.TPC100600003,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(promotionDO.getMerchantId(),loginAuthDto);
        PromotionVo promotionVo = BeanConverter.convert(promotionDO,PromotionVo.class);

        PromotionUseTypeEnum typeEnum = PromotionUseTypeEnum.getByType(promotionDO.getUseType());
        if (PromotionUseTypeEnum.ALL != typeEnum){
            PromotionRelationDO param = new PromotionRelationDO();
            param.setPromotionId(promotionDO.getId());
            List<PromotionRelationDO> relationDOS = promotionRelationService.select(param);

            List<PromotionRelationVo> relationVoList = BeanConverter.batchConvert(relationDOS,PromotionRelationVo.class);
            promotionVo.setRelationList(relationVoList);
        }

        return promotionVo;
    }
}
