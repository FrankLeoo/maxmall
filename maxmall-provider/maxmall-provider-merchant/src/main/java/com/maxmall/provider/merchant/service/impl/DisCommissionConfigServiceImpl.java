package com.maxmall.provider.merchant.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.enums.CommonStatusEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.core.utils.PermissionCheckUtil;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.merchant.exception.UacBizException;
import com.maxmall.provider.merchant.mapper.merchant.DistrCommissionConfigDOMapper;
import com.maxmall.provider.merchant.model.domain.merchant.DistrCommissionConfigDO;
import com.maxmall.provider.merchant.model.dto.merchant.DisCommissionModifyDto;
import com.maxmall.provider.merchant.model.dto.merchant.DisCommissionQueryDto;
import com.maxmall.provider.merchant.model.vo.DisCommissionConfigVo;
import com.maxmall.provider.merchant.service.DisCommissionConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ivoter
 * @ClassName MerchantLevelConfigServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 会员等级配置
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DisCommissionConfigServiceImpl extends BaseService<DistrCommissionConfigDO> implements DisCommissionConfigService {

    @Autowired
    private DistrCommissionConfigDOMapper commissionConfigDOMapper;

    @Override
    public List<DisCommissionConfigVo> listAllCommission(UserTokenDto loginAuthDto) {
        DistrCommissionConfigDO param = new DistrCommissionConfigDO();
        param.setMerchantId(loginAuthDto.getMerchantId());
        param.setStatus(CommonStatusEnum.ENABLE.getStatus());

        List<DistrCommissionConfigDO> levelConfigDOList = commissionConfigDOMapper.select(param);
        List<DisCommissionConfigVo> result = BeanConverter.batchConvert(levelConfigDOList, DisCommissionConfigVo.class);

        return result;
    }

    @Override
    public PageInfo<DisCommissionConfigVo> queryCommissionListWithPage(DisCommissionQueryDto queryParam, UserTokenDto loginAuthDto) {
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
        Example example = new Example(DistrCommissionConfigDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus());

        List<DistrCommissionConfigDO> doList = commissionConfigDOMapper.selectByExample(example);
        PageInfo<DistrCommissionConfigDO> pageInfo = new PageInfo<>(doList);

        //转化
        List<DisCommissionConfigVo> responses = BeanConverter.batchConvert(doList, DisCommissionConfigVo.class);
        PageInfo<DisCommissionConfigVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    @Override
    public int saveOrModifyCommission(DisCommissionModifyDto modifyDto, UserTokenDto loginAuthDto) {
        Long id = modifyDto.getId();
        DistrCommissionConfigDO levleEntity = BeanConverter.convert(modifyDto, DistrCommissionConfigDO.class);
        levleEntity.setUpdateInfo(loginAuthDto);

        int result = 0;
        if (id != null){
            DistrCommissionConfigDO levelConfigDO = commissionConfigDOMapper.selectByPrimaryKey(id);
            if (levelConfigDO == null){
                throw new UacBizException(ErrorCodeEnum.MDC10021004,id);
            }
            PermissionCheckUtil.checkDataMerchantPermission(levelConfigDO.getMerchantId(),loginAuthDto);
            result = commissionConfigDOMapper.updateByPrimaryKeySelective(levleEntity);
        }else{
            levleEntity.setMerchantId(loginAuthDto.getMerchantId());
            levleEntity.setStatus(CommonStatusEnum.ENABLE.getStatus());

            result = commissionConfigDOMapper.insert(levleEntity);
        }
        return result;
    }

    @Override
    public int deleteCommission(Long id, UserTokenDto loginAuthDto) {
        DistrCommissionConfigDO levelConfigDO = commissionConfigDOMapper.selectByPrimaryKey(id);
        if (levelConfigDO == null){
            throw new UacBizException(ErrorCodeEnum.MDC10021004,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(levelConfigDO.getMerchantId(),loginAuthDto);

        DistrCommissionConfigDO updateDO = new DistrCommissionConfigDO();
        updateDO.setId(id);
        updateDO.setStatus(CommonStatusEnum.DISABLE.getStatus());
        updateDO.setUpdateInfo(loginAuthDto);
        return commissionConfigDOMapper.updateByPrimaryKeySelective(updateDO);
    }

    @Override
    public DisCommissionConfigVo commissionDetail(Long id, UserTokenDto loginAuthDto) {
        DistrCommissionConfigDO levelConfigDO = commissionConfigDOMapper.selectByPrimaryKey(id);
        if (levelConfigDO == null){
            throw new UacBizException(ErrorCodeEnum.MDC10021004,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(levelConfigDO.getMerchantId(),loginAuthDto);

        return BeanConverter.convert(levelConfigDO, DisCommissionConfigVo.class);
    }
}
