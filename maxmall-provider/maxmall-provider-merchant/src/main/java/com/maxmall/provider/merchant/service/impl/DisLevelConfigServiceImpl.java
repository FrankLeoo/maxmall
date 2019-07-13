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
import com.maxmall.provider.merchant.mapper.merchant.DistributorLevelConfigDOMapper;
import com.maxmall.provider.merchant.model.domain.merchant.DistributorLevelConfigDO;
import com.maxmall.provider.merchant.model.dto.merchant.DisLevelModifyDto;
import com.maxmall.provider.merchant.model.dto.merchant.DisLevelQueryDto;
import com.maxmall.provider.merchant.model.vo.DistributorLevelConfigVo;
import com.maxmall.provider.merchant.service.DisLevelConfigService;
import org.apache.commons.lang3.StringUtils;
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
public class DisLevelConfigServiceImpl extends BaseService<DistributorLevelConfigDO> implements DisLevelConfigService {

    @Autowired
    private DistributorLevelConfigDOMapper distributorLevelConfigDOMapper;

    @Override
    public List<DistributorLevelConfigVo> listAllLevel(UserTokenDto loginAuthDto) {
        DistributorLevelConfigDO param = new DistributorLevelConfigDO();
        param.setMerchantId(loginAuthDto.getMerchantId());
        param.setStatus(CommonStatusEnum.ENABLE.getStatus());

        List<DistributorLevelConfigDO> levelConfigDOList = distributorLevelConfigDOMapper.select(param);
        List<DistributorLevelConfigVo> result = BeanConverter.batchConvert(levelConfigDOList,DistributorLevelConfigVo.class);

        return result;
    }

    @Override
    public PageInfo<DistributorLevelConfigVo> queryLevelListWithPage(DisLevelQueryDto queryParam, UserTokenDto loginAuthDto) {
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
        Example example = new Example(DistributorLevelConfigDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus());

        if (StringUtils.isNotEmpty(queryParam.getName())){
            criteria.andLike("name","%"+queryParam.getName()+"%");
        }

        List<DistributorLevelConfigDO> doList = distributorLevelConfigDOMapper.selectByExample(example);
        PageInfo<DistributorLevelConfigDO> pageInfo = new PageInfo<>(doList);

        //转化
        List<DistributorLevelConfigVo> responses = BeanConverter.batchConvert(doList,DistributorLevelConfigVo.class);
        PageInfo<DistributorLevelConfigVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    @Override
    public int saveOrModifyLevel(DisLevelModifyDto levelModifyDto, UserTokenDto loginAuthDto) {
        Long id = levelModifyDto.getId();
        DistributorLevelConfigDO levleEntity = BeanConverter.convert(levelModifyDto,DistributorLevelConfigDO.class);
        levleEntity.setUpdateInfo(loginAuthDto);

        int result = 0;
        if (id != null){
            DistributorLevelConfigDO levelConfigDO = distributorLevelConfigDOMapper.selectByPrimaryKey(id);
            if (levelConfigDO == null){
                throw new UacBizException(ErrorCodeEnum.MDC10021004,id);
            }
            PermissionCheckUtil.checkDataMerchantPermission(levelConfigDO.getMerchantId(),loginAuthDto);
            result = distributorLevelConfigDOMapper.updateByPrimaryKeySelective(levleEntity);
        }else{
            levleEntity.setMerchantId(loginAuthDto.getMerchantId());
            levleEntity.setStatus(CommonStatusEnum.ENABLE.getStatus());

            result = distributorLevelConfigDOMapper.insert(levleEntity);
        }
        return result;
    }

    @Override
    public int deleteLevel(Long id, UserTokenDto loginAuthDto) {
        DistributorLevelConfigDO levelConfigDO = distributorLevelConfigDOMapper.selectByPrimaryKey(id);
        if (levelConfigDO == null){
            throw new UacBizException(ErrorCodeEnum.MDC10021004,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(levelConfigDO.getMerchantId(),loginAuthDto);

        DistributorLevelConfigDO updateDO = new DistributorLevelConfigDO();
        updateDO.setId(id);
        updateDO.setStatus(CommonStatusEnum.DISABLE.getStatus());
        updateDO.setUpdateInfo(loginAuthDto);
        return distributorLevelConfigDOMapper.updateByPrimaryKeySelective(updateDO);
    }

    @Override
    public DistributorLevelConfigVo levelDetail(Long id, UserTokenDto loginAuthDto) {
        DistributorLevelConfigDO levelConfigDO = distributorLevelConfigDOMapper.selectByPrimaryKey(id);
        if (levelConfigDO == null){
            throw new UacBizException(ErrorCodeEnum.MDC10021004,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(levelConfigDO.getMerchantId(),loginAuthDto);

        return BeanConverter.convert(levelConfigDO,DistributorLevelConfigVo.class);
    }
}
