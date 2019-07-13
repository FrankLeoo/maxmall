package com.maxmall.provider.merchant.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.merchant.exception.UacBizException;
import com.maxmall.provider.merchant.mapper.merchant.DistributorAuditDOMapper;
import com.maxmall.provider.merchant.mapper.merchant.DistributorDOMapper;
import com.maxmall.provider.merchant.mapper.merchant.DistributorLevelConfigDOMapper;
import com.maxmall.provider.merchant.mapper.merchant.ShopDOMapper;
import com.maxmall.provider.merchant.model.domain.merchant.DistributorAuditDO;
import com.maxmall.provider.merchant.model.domain.merchant.DistributorDO;
import com.maxmall.provider.merchant.model.domain.merchant.ShopDO;
import com.maxmall.provider.merchant.model.dto.distributor.ModifyDistributorDto;
import com.maxmall.provider.merchant.model.dto.distributor.QueryDistrbutorAuditDto;
import com.maxmall.provider.merchant.model.vo.DistributorAuditVo;
import com.maxmall.provider.merchant.model.vo.ShopVo;
import com.maxmall.provider.merchant.service.DistributorAuditService;
import com.maxmall.provider.merchant.utils.PermissionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * The class 分销商审核管理.
 *
 * @author maxmall.net@gmail.com
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class DistributorAuditServiceImpl extends BaseService<DistributorAuditDO> implements DistributorAuditService {

    @Autowired
    private DistributorAuditDOMapper distributorAuditDOMapper;
    @Autowired
    private DistributorDOMapper distributorDOMapper;
    @Autowired
    private DistributorLevelConfigDOMapper levelConfigDOMapper;
    @Autowired
    private ShopDOMapper shopDOMapper;

    /**
     * 分页查询分销商申请
     * @param queryDistrbutorDto
     * @param loginAuthDto
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public PageInfo<DistributorAuditVo> queryAuditListWithPage(QueryDistrbutorAuditDto queryDistrbutorDto, UserTokenDto loginAuthDto) {
        Integer pageNum = queryDistrbutorDto.getPageNum();

        Integer pageSize = queryDistrbutorDto.getPageSize();
        if (pageNum ==null || pageNum <=0){
            pageNum = 1;
        }
        if (pageSize ==null || pageSize <=0 || pageSize>100){
            pageSize = 20;
        }
        PageHelper.startPage(pageNum, pageSize);
        //查询参数
        Example example = new Example(DistributorAuditDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("parentId", queryDistrbutorDto.getParentId()).andEqualTo("status", queryDistrbutorDto.getStatus());

        if (StringUtils.isNotEmpty(queryDistrbutorDto.getDistributorSn())){
            criteria.andLike("distributorSn","%"+queryDistrbutorDto.getDistributorSn()+"%");
        }
        List<DistributorAuditDO> distributorDOList = distributorAuditDOMapper.selectByExample(example);
        PageInfo<DistributorAuditDO> pageInfo = new PageInfo<>(distributorDOList);

        //转化
        List<DistributorAuditVo> responses = BeanConverter.batchConvert(distributorDOList,DistributorAuditVo.class);
        PageInfo<DistributorAuditVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        //获取门店
        responses.forEach(item -> {
            ShopDO shopDO = shopDOMapper.selectByPrimaryKey(item.getShopId());
            if (shopDO != null){
                item.setShop(BeanConverter.convert(shopDO, ShopVo.class));
            }
        });
        result.setList(responses);

        return result;
    }

    /**
     * 审核 申请
     * @param modifyDistributorDto
     * @param loginAuthDto
     * @return
     */
    @Override
    public int auditDistributor(ModifyDistributorDto modifyDistributorDto, UserTokenDto loginAuthDto) {

        DistributorAuditDO auditDO = distributorAuditDOMapper.selectByPrimaryKey(modifyDistributorDto.getId());
        if (auditDO == null){
            throw new UacBizException(ErrorCodeEnum.UAC10016001,modifyDistributorDto.getId());
        }
        if (auditDO.getStatus() != GlobalConstant.CommonStatusEnum.AUDIT.getStatus()){
            throw new UacBizException(ErrorCodeEnum.UAC10016003,modifyDistributorDto.getId());
        }
        //判断审核权限
        PermissionUtil.checkDataMerchantPermission(auditDO.getMerchantId(),loginAuthDto);
        //判断分销商是否存在
        DistributorDO param = new DistributorDO();
        param.setManagerUserId(auditDO.getManagerUserId());
        param.setMerchantId(auditDO.getMerchantId());
        int count = distributorDOMapper.selectCount(param);

        if (count > 0){
            throw new UacBizException(ErrorCodeEnum.UAC10016002,modifyDistributorDto.getId());
        }
        DistributorAuditDO auditEntity = new DistributorAuditDO();
        auditEntity.setId(auditDO.getId());
        //进行审核
        auditEntity.setStatus(modifyDistributorDto.getStatus());
        auditEntity.setUpdateInfo(loginAuthDto);
        auditEntity.setHandleManId(loginAuthDto.getUserId());
        auditEntity.setHandleManName(loginAuthDto.getUserName());
        auditEntity.setHandleNote(modifyDistributorDto.getHandleNote());
        auditEntity.setHandleTime(new Date());

        int result = distributorAuditDOMapper.updateByPrimaryKeySelective(auditEntity);
        //审核通过
        if (GlobalConstant.CommonStatusEnum.ENABLE.getStatus() == modifyDistributorDto.getStatus()){
            DistributorDO distributorDO = BeanConverter.convert(auditEntity,DistributorDO.class);
            distributorDO.setId(null);
            distributorDO.setStatus(GlobalConstant.CommonStatusEnum.ENABLE.getStatus());
            distributorDO.setUpdateInfo(loginAuthDto);

            distributorDOMapper.insert(distributorDO);
        }
        return result;
    }
}
