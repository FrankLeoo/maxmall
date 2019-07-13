package com.maxmall.provider.merchant.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.enums.CommonStatusEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.merchant.exception.UacBizException;
import com.maxmall.provider.merchant.mapper.merchant.DistrMoneyHistoryDOMapper;
import com.maxmall.provider.merchant.mapper.merchant.DistributorDOMapper;
import com.maxmall.provider.merchant.mapper.merchant.ShopDOMapper;
import com.maxmall.provider.merchant.model.domain.merchant.DistrMoneyHistoryDO;
import com.maxmall.provider.merchant.model.domain.merchant.DistributorDO;
import com.maxmall.provider.merchant.model.domain.merchant.ShopDO;
import com.maxmall.provider.merchant.model.dto.distributor.DistributorRadioDto;
import com.maxmall.provider.merchant.model.dto.distributor.QueryDistrHistoryDto;
import com.maxmall.provider.merchant.model.dto.distributor.QueryDistrbutorDto;
import com.maxmall.provider.merchant.model.vo.DistrMoneyHistoryVo;
import com.maxmall.provider.merchant.model.vo.DistributorVo;
import com.maxmall.provider.merchant.model.vo.ShopVo;
import com.maxmall.provider.merchant.service.DistributorService;
import com.maxmall.provider.merchant.utils.PermissionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * The class 分销商管理.
 *
 * @author maxmall.net@gmail.com
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class DistributorServiceImpl extends BaseService<DistributorDO> implements DistributorService {

    @Autowired
    private DistributorDOMapper distributorDOMapper;
    @Autowired
    private ShopDOMapper shopDOMapper;
    @Autowired
    private DistrMoneyHistoryDOMapper moneyHistoryDOMapper;

    /**
     * 分页查询商户子用户
     *
     * @param queryDistrbutorDto
     * @param loginAuthDto
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public PageInfo<DistributorVo> queryDistributorListWithPage(QueryDistrbutorDto queryDistrbutorDto, UserTokenDto loginAuthDto) {
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
        Example example = new Example(DistributorDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("parentId", queryDistrbutorDto.getParentId()).andEqualTo("status", queryDistrbutorDto.getStatus());

        if (StringUtils.isNotEmpty(queryDistrbutorDto.getDistributorSn())){
            criteria.andLike("distributorSn","%"+queryDistrbutorDto.getDistributorSn()+"%");
        }
        List<DistributorDO> distributorDOList = distributorDOMapper.selectByExample(example);
        PageInfo<DistributorDO> pageInfo = new PageInfo<>(distributorDOList);

        //转化
        List<DistributorVo> responses = BeanConverter.batchConvert(distributorDOList,DistributorVo.class);
        //获取门店
        responses.forEach(item -> {
            ShopDO shopDO = shopDOMapper.selectByPrimaryKey(item.getShopId());
            if (shopDO != null){
                item.setShop(BeanConverter.convert(shopDO,ShopVo.class));
            }
        });
        PageInfo<DistributorVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    /**
     * 根据ID查询商户详情
     * @param distributorId
     * @param loginAuthDto
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public DistributorVo queryByDistributorId(Long distributorId, UserTokenDto loginAuthDto) {
        DistributorDO distributorDO = distributorDOMapper.selectByPrimaryKey(distributorId);

        if (distributorDO == null){
            throw new UacBizException(ErrorCodeEnum.UAC10016001,distributorId);
        }
        //权限验证
        PermissionUtil.checkDataMerchantPermission(distributorDO.getMerchantId(),loginAuthDto);

        DistributorVo result = BeanConverter.convert(distributorDO,DistributorVo.class);
        //获取门店信息
        ShopDO shopDO = shopDOMapper.selectByPrimaryKey(distributorDO.getShopId());
        if (shopDO != null){
            ShopVo shopVo = BeanConverter.convert(shopDO,ShopVo.class);
            result.setShop(shopVo);
        }
        //上级分销商
        if (distributorDO.getParentId()!=null){
            DistributorDO parentDo = distributorDOMapper.selectByPrimaryKey(distributorDO.getParentId());
            if (parentDo != null){
                result.setParent(BeanConverter.convert(parentDo,DistributorVo.class));
            }
        }

        return result;
    }

    /**
     * 根据Id修改分销商分销比例.
     * @param distributorRadioDto
     * @param loginAuthDto
     * @return
     */
    @Override
    public Integer modifySalesRatio(DistributorRadioDto distributorRadioDto, UserTokenDto loginAuthDto) {
        DistributorDO distributorDO = distributorDOMapper.selectByPrimaryKey(distributorRadioDto.getId());

        if (distributorDO == null){
            throw new UacBizException(ErrorCodeEnum.UAC10016001,distributorRadioDto.getId());
        }
        //修改比例
        DistributorDO update = BeanConverter.convert(distributorRadioDto,DistributorDO.class);
        update.setUpdateInfo(loginAuthDto);

        return distributorDOMapper.updateByPrimaryKeySelective(update);
    }

    @Override
    public List<DistributorVo> distributorListAll(UserTokenDto loginAuthDto) {
        Example example = new Example(DistributorDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus());

        List<DistributorDO> distributorDOList = distributorDOMapper.selectByExample(example);
        return BeanConverter.batchConvert(distributorDOList,DistributorVo.class);
    }

    @Override
    public PageInfo<DistrMoneyHistoryVo> queryHistoryListWithPage(QueryDistrHistoryDto queryDistrHistoryDto, UserTokenDto loginAuthDto) {
        Integer pageNum = queryDistrHistoryDto.getPageNum();

        Integer pageSize = queryDistrHistoryDto.getPageSize();
        if (pageNum ==null || pageNum <=0){
            pageNum = 1;
        }
        if (pageSize ==null || pageSize <=0 || pageSize>100){
            pageSize = 20;
        }
        PageHelper.startPage(pageNum, pageSize);
        //查询参数
        Example example = new Example(DistrMoneyHistoryDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("distributorId", queryDistrHistoryDto.getDistributorId()).andEqualTo("type", queryDistrHistoryDto.getType());

        List<DistrMoneyHistoryDO> distributorDOList = moneyHistoryDOMapper.selectByExample(example);
        PageInfo<DistrMoneyHistoryDO> pageInfo = new PageInfo<>(distributorDOList);

        //转化
        List<DistrMoneyHistoryVo> responses = BeanConverter.batchConvert(distributorDOList,DistrMoneyHistoryVo.class);
        PageInfo<DistrMoneyHistoryVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }
}
