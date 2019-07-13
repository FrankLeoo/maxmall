package com.maxmall.provider.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.enums.CommonStatusEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.core.utils.PermissionCheckUtil;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.order.exception.OrderBizException;
import com.maxmall.provider.order.mapper.OrderReturnReasonDOMapper;
import com.maxmall.provider.order.model.domain.OrderReturnReasonDO;
import com.maxmall.provider.order.model.dto.OrderReturnReasonDto;
import com.maxmall.provider.order.model.dto.ReturnReasonQueryDto;
import com.maxmall.provider.order.model.vo.OrderReturnReasonVo;
import com.maxmall.provider.order.service.OrderReturnReasonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ivoter
 * @ClassName OrderReturnReasonServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 退款原因管理service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class OrderReturnReasonServiceImpl extends BaseService<OrderReturnReasonDO> implements OrderReturnReasonService {

    @Autowired
    private OrderReturnReasonDOMapper orderReturnReasonDOMapper;

    /**
     * 保存退款原因
     * @param returnReason
     * @param loginAuthDto
     * @return
     */
    @Override
    public int saveReturnReason(OrderReturnReasonDto returnReason, UserTokenDto loginAuthDto) {

        OrderReturnReasonDO entity = BeanConverter.convert(returnReason,OrderReturnReasonDO.class);
        entity.setMerchantId(loginAuthDto.getMerchantId());
        entity.setStatus(CommonStatusEnum.ENABLE.getStatus());
        entity.setUpdateInfo(loginAuthDto);

        return orderReturnReasonDOMapper.insert(entity);
    }

    /**
     * 更新原因
     * @param id
     * @param returnReason
     * @param loginAuthDto
     * @return
     */
    @Override
    public int updateReturnReason(Long id, OrderReturnReasonDto returnReason, UserTokenDto loginAuthDto) {
        OrderReturnReasonDO entity = orderReturnReasonDOMapper.selectByPrimaryKey(id);
        if (entity == null){
            return 0;
        }
        //验证权限
        PermissionCheckUtil.checkDataMerchantPermission(entity.getMerchantId(),loginAuthDto);

        OrderReturnReasonDO update = BeanConverter.convert(returnReason,OrderReturnReasonDO.class);
        update.setId(id);
        update.setUpdateInfo(loginAuthDto);

        return orderReturnReasonDOMapper.updateByPrimaryKeySelective(update);
    }

    /**
     * 根据id删除数据
     * @param ids
     * @param loginAuthDto
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids, UserTokenDto loginAuthDto) {
        if (CollectionUtils.isEmpty(ids)){
            return 0;
        }
        Example example = new Example(OrderReturnReasonDO.class);
        example.createCriteria().andIn("id",ids);
        List<OrderReturnReasonDO> reasonDOList = orderReturnReasonDOMapper.selectByExample(example);

        //验证
        reasonDOList.stream().forEach(item -> PermissionCheckUtil.checkDataMerchantPermission(item.getMerchantId(),loginAuthDto));

        OrderReturnReasonDO reasonDO = new OrderReturnReasonDO();
        reasonDO.setStatus(CommonStatusEnum.DISABLE.getStatus());
        reasonDO.setUpdateInfo(loginAuthDto);
        return orderReturnReasonDOMapper.updateByExampleSelective(reasonDO,example);
    }

    /**
     * 分页查询数据
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    @Override
    public PageInfo<OrderReturnReasonVo> queryReasontListWithPage(ReturnReasonQueryDto queryParam, UserTokenDto loginAuthDto) {
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
        Example example = new Example(OrderReturnReasonDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus());

        if (StringUtils.isNotEmpty(queryParam.getName())){
            criteria.andLike("name","%"+queryParam.getName()+"%");
        }
        List<OrderReturnReasonDO> uacUserList = orderReturnReasonDOMapper.selectByExample(example);
        PageInfo<OrderReturnReasonDO> pageInfo = new PageInfo<>(uacUserList);

        //转化
        List<OrderReturnReasonVo> responses = BeanConverter.batchConvert(uacUserList,OrderReturnReasonVo.class);
        PageInfo<OrderReturnReasonVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    /**
     * 订单详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    @Override
    public OrderReturnReasonVo reasonDetail(Long id, UserTokenDto loginAuthDto) {
        OrderReturnReasonDO entity = orderReturnReasonDOMapper.selectByPrimaryKey(id);
        if (entity == null){
            throw new OrderBizException(ErrorCodeEnum.OMC10031005);
        }
        PermissionCheckUtil.checkDataMerchantPermission(entity.getMerchantId(),loginAuthDto);

        return BeanConverter.convert(entity,OrderReturnReasonVo.class);
    }

    /**
     * 批量更新状态
     * @param ids
     * @param status
     * @param loginAuthDto
     * @return
     */
    @Override
    public int updateStatus(List<Long> ids, Integer status, UserTokenDto loginAuthDto) {
        if (CollectionUtils.isEmpty(ids)){
            return 0;
        }
        Example example = new Example(OrderReturnReasonDO.class);
        example.createCriteria().andIn("id",ids);
        List<OrderReturnReasonDO> reasonDOList = orderReturnReasonDOMapper.selectByExample(example);

        //验证
        reasonDOList.stream().forEach(item -> {
            PermissionCheckUtil.checkDataMerchantPermission(item.getMerchantId(), loginAuthDto);

            OrderReturnReasonDO entity = new OrderReturnReasonDO();
            entity.setId(item.getId());
            entity.setStatus(status);
            entity.setUpdateInfo(loginAuthDto);

            orderReturnReasonDOMapper.updateByPrimaryKeySelective(entity);
        });

        return reasonDOList.size();
    }
}
