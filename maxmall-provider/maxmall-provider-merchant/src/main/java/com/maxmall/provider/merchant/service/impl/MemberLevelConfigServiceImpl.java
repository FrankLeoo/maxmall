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
import com.maxmall.provider.merchant.mapper.merchant.MemberLevelConfigDOMapper;
import com.maxmall.provider.merchant.model.domain.merchant.MemberLevelConfigDO;
import com.maxmall.provider.merchant.model.dto.merchant.MemberLevelModifyDto;
import com.maxmall.provider.merchant.model.dto.merchant.MemberLevelQueryDto;
import com.maxmall.provider.merchant.model.vo.MemberLevelConfigVo;
import com.maxmall.provider.merchant.service.MemberLevelConfigService;
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
public class MemberLevelConfigServiceImpl extends BaseService<MemberLevelConfigDO> implements MemberLevelConfigService {

    @Autowired
    private MemberLevelConfigDOMapper memberLevelConfigDOMapper;

    @Override
    public List<MemberLevelConfigVo> listAllLevel(UserTokenDto loginAuthDto) {
        MemberLevelConfigDO param = new MemberLevelConfigDO();
        param.setMerchantId(loginAuthDto.getMerchantId());
        param.setStatus(CommonStatusEnum.ENABLE.getStatus());

        List<MemberLevelConfigDO> levelConfigDOList = memberLevelConfigDOMapper.select(param);
        List<MemberLevelConfigVo> result = BeanConverter.batchConvert(levelConfigDOList,MemberLevelConfigVo.class);

        return result;
    }

    @Override
    public PageInfo<MemberLevelConfigVo> queryLevelListWithPage(MemberLevelQueryDto queryParam, UserTokenDto loginAuthDto) {
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
        Example example = new Example(MemberLevelConfigDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus()).andEqualTo("level",queryParam.getLevel());

        if (StringUtils.isNotEmpty(queryParam.getName())){
            criteria.andLike("name","%"+queryParam.getName()+"%");
        }

        List<MemberLevelConfigDO> doList = memberLevelConfigDOMapper.selectByExample(example);
        PageInfo<MemberLevelConfigDO> pageInfo = new PageInfo<>(doList);

        //转化
        List<MemberLevelConfigVo> responses = BeanConverter.batchConvert(doList,MemberLevelConfigVo.class);
        PageInfo<MemberLevelConfigVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    @Override
    public int saveOrModifyLevel(MemberLevelModifyDto levelModifyDto, UserTokenDto loginAuthDto) {
        Long id = levelModifyDto.getId();
        MemberLevelConfigDO levleEntity = BeanConverter.convert(levelModifyDto,MemberLevelConfigDO.class);
        levleEntity.setUpdateInfo(loginAuthDto);

        int result = 0;
        if (id != null){
            MemberLevelConfigDO levelConfigDO = memberLevelConfigDOMapper.selectByPrimaryKey(id);
            if (levelConfigDO == null){
                throw new UacBizException(ErrorCodeEnum.MDC10021004,id);
            }
            PermissionCheckUtil.checkDataMerchantPermission(levelConfigDO.getMerchantId(),loginAuthDto);
            result = memberLevelConfigDOMapper.updateByPrimaryKeySelective(levleEntity);
            // TODO 更新用户的等级

        }else{
            levleEntity.setMerchantId(loginAuthDto.getMerchantId());
            levleEntity.setStatus(CommonStatusEnum.ENABLE.getStatus());

            result = memberLevelConfigDOMapper.insert(levleEntity);
        }
        return result;
    }

    @Override
    public int deleteLevel(Long id, UserTokenDto loginAuthDto) {
        MemberLevelConfigDO levelConfigDO = memberLevelConfigDOMapper.selectByPrimaryKey(id);
        if (levelConfigDO == null){
            throw new UacBizException(ErrorCodeEnum.MDC10021004,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(levelConfigDO.getMerchantId(),loginAuthDto);

        MemberLevelConfigDO updateDO = new MemberLevelConfigDO();
        updateDO.setId(id);
        updateDO.setStatus(CommonStatusEnum.DISABLE.getStatus());
        updateDO.setUpdateInfo(loginAuthDto);
        return memberLevelConfigDOMapper.updateByPrimaryKeySelective(updateDO);
    }

    @Override
    public MemberLevelConfigVo levelDetail(Long id, UserTokenDto loginAuthDto) {
        MemberLevelConfigDO levelConfigDO = memberLevelConfigDOMapper.selectByPrimaryKey(id);
        if (levelConfigDO == null){
            throw new UacBizException(ErrorCodeEnum.MDC10021004,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(levelConfigDO.getMerchantId(),loginAuthDto);

        return BeanConverter.convert(levelConfigDO,MemberLevelConfigVo.class);
    }
}
